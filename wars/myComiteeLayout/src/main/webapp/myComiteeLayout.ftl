<div class="myComiteeLayout" id="main-content" role="main">
	<div class="portlet-layout row">
		<div class="col-md-12 portlet-column portlet-column-only" id="column-1">
			${processor.processColumn("column-1", "portlet-column-content portlet-column-content-only")}
		</div>
	</div>

	<div class="portlet-layout row">
		<div class="col-md-12 portlet-column portlet-column-first" id="column-2">
			${processor.processColumn("column-2", "portlet-column-content portlet-column-content-first")}
		</div>
	</div>

	<div class="portlet-layout row">
		<div class="col-lg-6 col-md-7 col-sm-4 portlet-column portlet-column-only" id="column-3">
			${processor.processColumn("column-3", "portlet-column-content portlet-column-content-only")}
		</div>
	
		<div class="col-lg-6 col-md-3 col-sm-4 portlet-column portlet-column-first" id="column-4">
			${processor.processColumn("column-4", "portlet-column-content portlet-column-content-first")}
			<div class="col-lg-12 col-md-3 col-sm-4 portlet-column portlet-column-first" id="column-5">
				${processor.processColumn("column-5", "portlet-column-content portlet-column-content-second")}
			</div>
		</div>
	</div>
</div>