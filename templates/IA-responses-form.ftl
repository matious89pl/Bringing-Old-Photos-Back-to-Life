<div id="iaResponses">
    <!-- CONSULTATION-QA-A -->
    <div id="templateA">
        <!-- <p>Documents and Media </p> -->
        <p>Respondent Email Address <input type="text" name="id" size="40"></p>
        <label class="containerStatus">
            <p>Confidential response <input type="checkbox" name="confidentialResponse" size="40"><span class="checkBoxCustom"> </span></p>
        </label>
        <button class="save" onclick="saveRowDDL()"> Save </button> <!-- link with back in order to save the response in the correct DDL -->
    </div>

    <!-- CONSULTATION-QA-B -->
    <div id="templateB">
        <p> Question 1 <input type="text" name="questionOne" size="40"></p>
        <p> Question 2 <input type="text" name="questionTwo" size="40"></p>
        <!-- <p>Documents and Media </p> -->
        <p>Respondent Email Address <input type="text" name="id" size="40"></p>
        <label class="containerStatus">
            <p>Confidential response <input type="checkbox" name="confidentialResponse" size="40"><span class="checkBoxCustom"> </span></p>
        </label>
        <button class="save" onclick="saveRowDDL()"> Save </button> <!-- link with back in order to save the response in the correct DDL-->
    </div>
</div>


<script>
    $(document).ready(function(){
        console.log("IN DOCUMENT READY!");
        var queryString = window.location.search;
        var urlParams = new URLSearchParams(queryString);
        var idParam = urlParams.get('responseTemplate');
        console.log("idParam: " + idParam);
        if (idParam == "A"){
            $("#templateA").css("display","block");

        } else if (idParam == "B"){
            $("#templateB").css("display","block");
        }
        
  });


  function saveRowDDL(){
      console.log("ONSAVE FUNCTION");
  }

</script>


<style>
    #templateA, #templateB {
      display:none;
      font-size: 20px;
    }

    #iaResponses input[type="text"] {
        border: none;
        border-radius: 2px;
        box-shadow: 0px 0px 4px 0px rgb(0 0 0 / 30%);
        margin-left: 20px;
    }
    
    .save{
        background: #b1c568;
        border-radius: 5px;
        color: white;
        font-size: 12px;
        font-weight: normal;
        letter-spacing: .25px;
        line-height: 18px;
        height: 36px;
        border: 0;
        padding: 9px 16px 9px 17px;
        display: -webkit-box;
    }

    #iaResponses label{
        color: black;
        font-size: 20px !important;     
        font-weight: normal !important;
    }

    .containerStatus input {
        position: absolute;
        opacity: 0;
        cursor: pointer;
        height: 0;
        width: 0;
    }

    .checkBoxCustom {
        position: absolute;
        margin-left: 15px;
        height: 23px;
        width: 23px;
        background-color: white;
        margin-right: 10px;
        border-radius: 2px;
        box-shadow: 0px 0px 4px 0px rgb(0 0 0 / 30%);
    }

    .containerStatus input:checked ~ .checkBoxCustom{
        background-image: url(https://webserver-recportal-dev.lfr.cloud//o/rec-theme/images/forms/check-gray.svg);
        background-size: contain;
    }



</style>
