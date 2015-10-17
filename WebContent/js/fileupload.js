Ext.onReady(function(){
Ext.QuickTips.init();

var lUploadForm = new Ext.FormPanel({
	x : 10,
	y : 10,
	renderTo : 'fileupload',
	height : 100,
	width : 600,
	title : 'UploadFiles',
	fileUpload : true,
	frame : true,
	layout : 'absolute',
	items : [
	{
		x : 225,
		y : 6,
		xtype : 'fileuploadfield',
		id : 'upload',
		buttonText : 'Select & Upload',
		buttonCfg: {
			
        },
		width : 115,
		buttonOnly : true,
		listeners : {
			'fileselected' : function(fb,v){
				var el = Ext.fly('filepath');
				el.update(v.replace("C:\\fakepath\\", ""));
				Ext.MessageBox.confirm('Confirm','File selected is, </br>' + v.replace("C:\\fakepath\\","")+ '</br></br>Do you want to upload it?',function(btn){
					if(btn=="yes")
					{
						if(lUploadForm.getForm().isValid()){
							lUploadForm.getForm().submit({
								url : 'ReadFileData.kar',
								waitMsg : 'Uploading File ....',
								success : function(lUploadForm, o)
								{
									console.log("suceess");
									console.log(o.result.ResponseStorage.SortedVaues);
								}
							});
						}
					}
					else{
						lUploadForm.getForm().reset();
					}
				});
			}
		}
		
	},
	{
		y : 40,
		x : 225,
		width : 500,
		xtype : 'displayfield',
		name : 'filepath',
		fieldLabel : 'Display field',
		value : '<span style="color:white;" id="filepath"></span>'
	}
	]
});	
	
	
	
});