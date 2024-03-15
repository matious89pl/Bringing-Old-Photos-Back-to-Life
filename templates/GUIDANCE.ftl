<div id="guidance">

<script>
	$(document).ready(function(){
	     $(".autofit-row.mb-4.metadata-author").hide();
	     $(".form-group.form-inline.input-checkbox-wrapper").hide();
	    //All tabcontent >0 hide
	    tabcontent = document.getElementsByClassName("tabcontent");
	    subtabcontent = document.getElementsByClassName("subtabcontent");
        for (i = 1; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
	 })
</script>

<script>
	$(document).ready(function(){
	     $(".autofit-row.mb-4.metadata-author").hide();
	     $(".form-group.form-inline.input-checkbox-wrapper").hide();
	    //All tabcontent >0 hide
	    tabcontent = document.getElementsByClassName("tabcontent");
	    subtabcontent = document.getElementsByClassName("subtabcontent");
        for (i = 1; i < tabcontent.length; i++) {
            tabcontent[i].style.display = "none";
        }
        for (i = 0; i < subtabcontent.length; i++) {
            subtabcontent[i].style.display = "none";
        }
	    
	 })
    
</script>

<script>
    function openTab(evt, tabName) {
      // Declare all variables
      var i, tabcontent, tabGuidance;

      // Get all elements with class="tabcontent" and hide them
      tabcontent = document.getElementsByClassName("tabcontent");
      for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
      }   
    
      // Get all elements with class="tabGuidance" and remove the class "active"
      tabGuidance = document.getElementsByClassName("tabGuidance");
      for (i = 0; i < tabGuidance.length; i++) {
        tabGuidance[i].className = tabGuidance[i].className.replace(" active", "");
      }
    
      // Show the current tab, and add an "active" class to the button that opened the tab
      document.getElementById(tabName).style.display = "block";
      evt.currentTarget.className += " active";

    }
    
</script>


<!-- Tab links -->
<div class="tabGui">
    <button class="tabGuidance mr-2 active" onclick="openTab(event, 'Gui-pat')">Performance Assurance Techniques</button>
    <button class="tabGuidance mr-2" onclick="openTab(event, 'Gui-paf')">Performance Assurance Framework</button>
    <button class="tabGuidance mr-2" onclick="openTab(event, 'Gui-rr')">Risk Register</button>
    <button class="tabGuidance mr-2" onclick="openTab(event, 'Gui-paop')">The Performance Assurance Operating Plan</button>
    <button class="tabGuidance mr-2" onclick="openTab(event, 'Gui-pam')">The Performance Assurance Methodology</button>
    <button class="tabGuidance" onclick="openTab(event, 'Gui-fg')">FAQs and Guides</button>
</div>

<div class="">
    <div id="Gui-pat" class="tabcontent">    
        <div class="Gui-portlet">
            <@liferay_portlet["runtime"] instanceId="pat" portletName="com_liferay_document_library_web_portlet_DLPortlet" /> 
        </div>
    </div>

    <div id="Gui-paf" class="tabcontent">
        <div class="Gui-portlet">
            <@liferay_portlet["runtime"] instanceId="paf" portletName="com_liferay_document_library_web_portlet_DLPortlet" /> 
        </div>
    </div>

    <div id="Gui-rr" class="tabcontent">
        <div class="Gui-portlet">
            <@liferay_portlet["runtime"] instanceId="rr" portletName="com_liferay_document_library_web_portlet_DLPortlet" /> 
        </div>
    </div>

    <div id="Gui-paop" class="tabcontent">
        <div class="Gui-portlet">
            <@liferay_portlet["runtime"] instanceId="paop" portletName="com_liferay_document_library_web_portlet_DLPortlet" /> 
        </div>
    </div>

    <div id="Gui-pam" class="tabcontent">
        <div class="Gui-portlet">
            <@liferay_portlet["runtime"] instanceId="pam" portletName="com_liferay_document_library_web_portlet_DLPortlet" /> 
        </div>
    </div>

    <div id="Gui-fg" class="tabcontent">
        <div class="Gui-portlet">
            <@liferay_portlet["runtime"] instanceId="fg" portletName="com_liferay_document_library_web_portlet_DLPortlet" /> 
        </div>
    </div>
</div>

</div>