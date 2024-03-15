<div class="comiteeLayout" id="main-content" role="main">
	<div class="portlet-layout row">
			<div class="col-md-12 portlet-column portlet-column-only" id="column-6">
				${processor.processColumn("column-6", "portlet-column-content portlet-column-content-only")}
			</div>
	</div>
	<div class="portlet-layout row">
		<div class="col-lg-6 col-md-7 col-sm-4 portlet-column portlet-column-only" id="column-1">
			${processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")}
		</div>
		
		<div class="col-lg-6 col-md-3 col-sm-4 portlet-column portlet-column-first" id="column-2">
			${processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")}
			<div class="col-lg-12 col-md-7 col-sm-4 portlet-column portlet-column-only" id="column-3" style="float:left;">
				${processor.processColumn("column-3", "portlet-column-content portlet-column-content-only")}
			</div>
		</div>
	</div>
</div>