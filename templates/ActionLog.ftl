<#--
Web content templates are used to lay out the fields defined in a web
content structure.

Please use the left panel to quickly add commonly used variables.
Autocomplete is also available and can be invoked by typing "${".
-->

     <div class="grid_item box actionLog">
                <h2> Action Log</h2>
                <div class="comittes">
                    <div class="grid_itemComittee ">  
                        <div class="selectorComitteesActionLog">  
                            <p class="myComitteesTitleActionLog left" onclick="openMyComitteesActionLog()">  My Committees </p>
                            <p class="allComitteesTitleActionLog right" onclick="openAllComitteesActionLog()"> All Committees </p>
                        </div>

                        <div class="myComittesActionLog">
                            <br><br> <p style="text-align: center;"> TBD </p> <br> <br> 
                        </div>
                        <div class="allComitteesActionLog">
                            <br><br> <p style="text-align: center;"> TBDD </p> <br> <br> 
                        </div>
                        
                    </div>
                </div>
            </div>
 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
<script>
        function openAllComitteesActionLog(){
            $(".allComitteesActionLog").css("visibility","visible");
            $(".allComitteesTitleActionLog").css("font-weight","700");
            $(".allComitteesTitleActionLog").css("border-bottom","solid");
            $(".allComitteesTitleActionLog").css("border-bottom-color","#4d8934");

            $(".myComittesActionLog").css("visibility","hidden");
            $(".myComitteesTitleActionLog").css("font-weight","normal");
            $(".myComitteesTitleActionLog").css("border-bottom","none");
            $(".myComitteesTitleActionLog").css("border-bottom-color","transparent");
        }

        function openMyComitteesActionLog(){
            $(".myComittesActionLog").css("visibility","visible");
            $(".myComitteesTitleActionLog").css("font-weight","700");
            $(".myComitteesTitleActionLog").css("border-bottom","solid");
            $(".myComitteesTitleActionLog").css("border-bottom-color","#4d8934");

            $(".allComitteesActionLog").css("visibility","hidden");
            $(".allComitteesTitleActionLog").css("font-weight","normal");
            $(".allComitteesTitleActionLog").css("border-bottom","none");
            $(".allComitteesTitleActionLog").css("border-bottom-color","transparent");
        }
</script>
