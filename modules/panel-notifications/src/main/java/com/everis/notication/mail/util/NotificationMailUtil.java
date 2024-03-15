package com.everis.notication.mail.util;

import com.everis.panel.notifications.portlet.PanelNotificationsPortlet;
import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.mail.internet.InternetAddress;

public class NotificationMailUtil {
    
	private static final Log logger = LogFactoryUtil.getLog(PanelNotificationsPortlet.class);
    
    /**
     * Send email.
     *
     * @param mail
     *            the mail
     */
    public static void sendEmail(MailMessage mail) {
        logger.debug("Entering sendMail from SESMailUtil");
        if (sesSetValidEmails(mail)) {
            logger.debug("Sending Mail!");
            MailServiceUtil.sendEmail(mail);
            logger.debug("Mail SENT!");
        } else {
            logger.error("No mails SENT because of internal error");
        }
        logger.debug("Exiting sendMail from SESMailUtil");
    }
    
    /**
     * Gets the valid emails.
     *
     * @param toAddresses
     *            the to addresses
     * @return the valid emails
     */
    public static InternetAddress[] getValidEmails(InternetAddress[] toAddresses) {
        logger.debug(String.format("Entering getValidEmails with toAddresses length=%s", toAddresses.length));
        String sesInvalidConstants = PropsUtil.get("ses.invalid.mail.constants");
        // valid addresses constants
        String[] splitsesInvalidConstants = sesInvalidConstants.split(",");
        String sesDefaultMail = PropsUtil.get("ses.default.mail");
        // final valid addresses where the message will be sent
        ArrayList<InternetAddress> listAddresses = new ArrayList<>();
        // address array position
        int i = 0;
        for (InternetAddress toAddress : toAddresses) {
            boolean invalid = false;
            // valid address constant position
            int j = 0;
            for (String sesInvalidConstant : splitsesInvalidConstants) {
                // do not send mail to this invalid mail because you do not know
                // sesDefaultMail
                if (toAddress.toString().contains((sesInvalidConstant)) && Validator.isNull(sesDefaultMail)) {
                    logger.warn("message not sent to " + toAddress.toString() + " because no sesDefaultMail defined");
                    break;
                } else if (toAddress.toString().contains((sesInvalidConstant))) {
                    logger.debug("invalid mail " + toAddress.getAddress().toString());
                    // difference mails
                    String aux = "+" + i + "@";
                    String emailAux = sesDefaultMail.replace("@", aux);
                    toAddress.setAddress(emailAux);
                    listAddresses.add(toAddress);
                    logger.debug("invalid default mail added " + toAddress.getAddress());
                    logger.debug("invalid Adresses To Personal" + toAddress.getPersonal());
                    break;
                } else if (j == splitsesInvalidConstants.length - 1 && !invalid) {
                    // add valid email to list
                    listAddresses.add(toAddress);
                    logger.debug("valid email added " + toAddress.getAddress());
                    logger.debug("valid Adresses To Personal" + toAddress.getPersonal());
                }
                ++j;
            }
            ++i;
        }
        InternetAddress[] validAddressesTo = new InternetAddress[listAddresses.size()];
        int k = 0;
        logger.debug("Valid addresses size " + listAddresses.size());
        for (InternetAddress address : listAddresses) {
            validAddressesTo[k] = address;
            ++k;
        }
        logger.debug(String.format("Exiting getValidEmails with validAddressesTo length=%s", validAddressesTo.length));
        return validAddressesTo;
    }
    
    /**
     * Check empty cases.
     *
     * @return true, if successful
     */
    // check validIps and defaultMail definition
    public static boolean checkEmptyCases() {
        logger.debug("Entering checkEmptyCases");
        
        String sesValidIPs = PropsUtil.get("ses.valid.IPs");
        String sesInvalidConstants = PropsUtil.get("ses.invalid.mail.constants");
        boolean result = false;
        if (Validator.isNull(sesValidIPs) || Validator.isNull(sesInvalidConstants)) {
            // do no normal flow, do not apply filter
            result = true;
        }
        logger.debug(String.format("Exiting checkEmptyCases as %s", result));
        return result;
    }
    
    /**
     * Ses set valid emails.
     *
     * @param mail
     *            the mail
     * @return true, if successful
     */
    // returns true if all goes OK. Return false for errors.
    public static boolean sesSetValidEmails(MailMessage mail) {
        logger.debug(String.format("Entering sesSetValidEmails for mail=%s", mail));
        if (checkEmptyCases()) {
            // if detects empty, do not apply filter, do normal flow, send to
            // all
            logger.debug("ses.valid.IPs or ses.invalid.mail.constants is empty -> SES is not applied");
            return true;
        }
        // get current server IP
        String currentServerIP = null;
        String serverIPAddress = StringPool.BLANK;
        try {
            Runtime rt = Runtime.getRuntime();
            String[] commands = { "bash", "-c",
                    "LANG=c ifconfig eth0 | grep 'inet addr' | awk -F: '{print $2}' | awk '{print $1}'" };
            Process proc = rt.exec(commands);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            // get default gateway
            while ((currentServerIP = stdInput.readLine()) != null) {
                if (Validator.isNotNull(currentServerIP)) {
                    InetAddress.getByName(currentServerIP).isReachable(3000);
                    serverIPAddress = currentServerIP;
                    logger.debug("asign: " + serverIPAddress);
                }
                logger.debug("ipAddresses(inside WHILE): " + serverIPAddress);
            }
        } catch (Exception e) {
            logger.error("Error getting server IP");
            return false;
        }
        
        if (Validator.isNotNull(serverIPAddress)) {
            currentServerIP = serverIPAddress;
            logger.debug("currentServerIP -> " + currentServerIP);
            boolean validIP = false;
            String sesValidIPs = PropsUtil.get("ses.valid.IPs");
            logger.debug("sesValidIPs -> " + sesValidIPs);
            String[] splitSESValidIPs = sesValidIPs.split(",");
            logger.debug("validIPs List -> " + sesValidIPs);
            for (String sesIP : splitSESValidIPs) {
                // only send emails from ourlounge server (defined in
                // portal-ext)
                if (sesIP.equals(currentServerIP)) {
                    validIP = true;
                    break;
                }
            }
            logger.debug("is a validIP? -> " + validIP);
            if (validIP) {
                // get only the valid emails
                InternetAddress[] validAddressesTo = getValidEmails(mail.getTo());
                logger.debug("valid mails to be sent " + validAddressesTo);
                mail.setTo(validAddressesTo);
                return true;
            } else {
                logger.warn("SES is not applied because the server IP is not in ses.valis.IPs list");
                return true;
            }
        }
        // null server ip address
        else {
            logger.error("Cannot get server IP address");
            return false;
        }
    }
    
}
