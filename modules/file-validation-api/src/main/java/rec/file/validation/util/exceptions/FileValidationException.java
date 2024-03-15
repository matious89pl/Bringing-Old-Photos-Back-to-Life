package rec.file.validation.util.exceptions;

import com.everis.rec.validation.log.service.ValidationLogLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import rec.file.validation.util.constants.FileValidationConstants;

import java.util.Date;

/**
 * @author mmaceasd
 */
public class FileValidationException extends Exception{

	static final Log _log = LogFactoryUtil.getLog(FileValidationException.class.getName());

	static String errorCode;
	static String errorMessage;

	public FileValidationException(String error) {
		super(error);
	}

	/**
	 * @return JSONObject
	 */
	public static JSONObject getError(long companyId, long groupId, String fileName, long userId, String uploadedFrom, long folderId, String errCode, int fileRow, int fileColumn, boolean has_header) {
		JSONObject response = JSONFactoryUtil.createJSONObject();
		String message = StringPool.BLANK;
		String reason;
		String row;
		String column;
		int columnIncrement = 1;
		int rowIncrement = 1;

		if (has_header) {
			rowIncrement++;
		}

		response.put("code", errCode);
		errorCode = errCode;
		
		switch (errCode) {
			case "R001":
				message = FileValidationConstants.ERROR_R001.replace(FileValidationConstants.REPLACE_FILENAME, fileName).replace(FileValidationConstants.REPLACE_REASON, FileValidationConstants.REJECTION_LEVEL_1);
				break;
			case "R002":
				message = FileValidationConstants.ERROR_R002.replace(FileValidationConstants.REPLACE_FILENAME, fileName).replace(FileValidationConstants.REPLACE_REASON, FileValidationConstants.REJECTION_LEVEL_2);
				break;
			case "R003":
				row = String.valueOf(fileRow);
				column = String.valueOf(fileColumn);
				reason = FileValidationConstants.REJECTION_LEVEL_3.replace(FileValidationConstants.REPLACE_ROW, row).replace(FileValidationConstants.REPLACE_COLUMN, column);
				message = FileValidationConstants.ERROR_R003.replace(FileValidationConstants.REPLACE_FILENAME, fileName).replace(FileValidationConstants.REPLACE_REASON, reason);
				break;
			case "R004":
				row = String.valueOf(fileRow + rowIncrement);
				column = String.valueOf(fileColumn + columnIncrement);
				reason = FileValidationConstants.REJECTION_LEVEL_4.replace(FileValidationConstants.REPLACE_ROW, row).replace(FileValidationConstants.REPLACE_COLUMN, column);
				message = FileValidationConstants.ERROR_R004.replace(FileValidationConstants.REPLACE_FILENAME, fileName).replace(FileValidationConstants.REPLACE_REASON, reason);
				break;
			case "R003Z":
				row = String.valueOf(fileRow);
				column = String.valueOf(fileColumn);
				reason = FileValidationConstants.REJECTION_LEVEL_3.replace(FileValidationConstants.REPLACE_ROW, row).replace(FileValidationConstants.REPLACE_COLUMN, column);
				message = FileValidationConstants.ERROR_R003_ZIP.replace(FileValidationConstants.REPLACE_FILENAME, fileName).replace(FileValidationConstants.REPLACE_REASON, reason);
				break;
			case "R004Z":
				row = String.valueOf(fileRow + rowIncrement);
				column = String.valueOf(fileColumn + columnIncrement);
				reason = FileValidationConstants.REJECTION_LEVEL_4.replace(FileValidationConstants.REPLACE_ROW, row).replace(FileValidationConstants.REPLACE_COLUMN, column);
				message = FileValidationConstants.ERROR_R004_ZIP.replace(FileValidationConstants.REPLACE_FILENAME, fileName).replace(FileValidationConstants.REPLACE_REASON, reason);
				break;
			default:
				_log.info("Error code " + errCode + " not included in rejection reasons");
				break;
		}

		response.put("message", message);
		errorMessage = message;

		_log.error("FILE VALIDATION ERROR - " + message + ", DATE: " + new Date() + ", ORGANISATION: " + groupId + ", FOLDER: " + folderId);

		/*
		try {
			User user = UserLocalServiceUtil.getUser(userId);
			String uploadedBy = user.getFullName();
			createReasonLog(companyId, groupId, fileName, uploadedBy, uploadedFrom, folderId, message);
		} catch (PortalException e) {
			_log.error("ERROR - Creating reason log", e);
		}
		*/

		return response;
	}

	/**
	 * 
	 */
	private static void createReasonLog(long companyId, long groupId, String fileName, String uploadedBy, String uploadedFrom, long folderId, String logReason) {
		ValidationLogLocalServiceUtil.addNewValidationLog(fileName, uploadedBy, uploadedFrom, folderId, logReason, groupId, companyId);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
