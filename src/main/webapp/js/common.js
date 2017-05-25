// JavaScript Document

function checkForm(formId)
{
	var formObj = document.getElementById(formId);
	
	//校验Input控件是否为空
	var inputObjs = formObj.getElementsByTagName('input');
	
	if(inputObjs != null && inputObjs.length != 0)
	{
		for(var i=0; i < inputObjs.length; i++)
		{
			var inputObj = inputObjs[i];
			if(inputObj.chkFlag == 1)
			{
				if(inputObj.type == 'text')
				{
					if(inputObj.value == null || inputObj.value == '')
					{
						alert('请输入'+inputObj.desc+'！');
						return false;
					}
				}
				else if(inputObj.type == 'password')
				{
					if(inputObj.value == null || inputObj.value == '')
					{
						alert('请输入'+inputObj.desc+'！');
						return false;
					}
				}
			}
		}
	}
	
	var selectObjs = formObj.getElementsByTagName('select');
	
	if(selectObjs != null && selectObjs.length != 0)
	{
		for(var i=0; i < selectObjs.length; i++)
		{
			var selectObj = selectObjs[i];
			if(selectObj.chkFlag == 1)
			{
				/*if(inputObj.type == 'text')
				{
					if(inputObj.value == null || inputObj.value == '')
					{
						alert('请输入'+inputObj.desc+'！');
						return false;
					}
				}
				else if(inputObj.type == 'password')
				{
					if(inputObj.value == null || inputObj.value == '')
					{
						alert('请输入'+inputObj.desc+'！');
						return false;
					}
				}*/
			}
		}
	}
	
	return true;
}

function submitForm(formId)
{
	if(checkForm(formId))
	{
		//alert('ok');
		document.getElementById(formId).submit();
	}
}