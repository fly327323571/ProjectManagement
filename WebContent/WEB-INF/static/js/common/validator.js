function Validator(config, $root){
	
	var _config = config;
	var _$root = $root ? $root : $("body");
	var validTip = '<span style="color:green;position: absolute;right: -5px;top: 5px;" class="validate glyphicon glyphicon-ok">OK.</span>';
	var errTip = '<span style="color:red;position: absolute;right: -5px;top: 5px;" class="validate glyphicon glyphicon-remove">NO.</span>'; 
	var loading = '<span style="position: absolute;right: -5px;top: 5px;" class="validate glyphicon"><img src="static/images/loading.gif" alt="" /></span>';

	//常用验证 正则表达式
	var regex = $.Validator.regex;
	
	function _init(){
		$.each(_config, function(i, v){
			_$root.on("change","#"+v.id,function(e){
				_validateField(v, false);
			});
		});
	}
	
	function _remind(remind, msg){
		var remindText = (remind && "string"==typeof remind) ? remind : msg;
		if(remind && "function" == typeof remind){
			remind(remindText);
			return;
		}
		alertFail(remindText);
	}
	
	function _requiredValidate($field, value, v, showDialog){
		var required = $field.attr("required");
		if(required=="required"){
			if(!value){
				if(showDialog){
					_remind(v.remind, v.name + " can't be empty!");
				}
				return false;
			}
		}
		return true;
	}
	
	function _regexValidate($field, value ,v, showDialog){
		var validateType = $field.attr("validateType") ? $field.attr("validateType").trim(): undefined;
		if(!validateType)return true;
		var valid = true;
		if(validateType){
			switch(validateType){
			case "email" : 
				valid = regex.email.test(value); break;
			case "telephone":
				valid = regex.telephone.test(value); break;
			case "cellphone" :
				valid = regex.cellphone.test(value); break;
			case "phoneNumber" :
				valid = regex.telephone.test(value) || regex.cellphone.test(value);break;
			case "userName" :
				valid = regex.userName.test(value); break;
			case "password" : 
				valid = value.length>=6 && value.length<=16 && !regex.password.test(value); break;
			case "productName" : case "storeName": case "shopName":
				valid = regex.productName.test(value); break;
			case "positiveInteger" :
				valid = regex.positiveInteger.test(value); break;
			case "positiveFloat" :
				valid = regex.positiveFloat.test(value); break;
			case "nonNegativeInteger":
				valid = regex.nonNegativeInteger.test(value); break;
			case "nonNegativeFloat":
				valid = regex.nonNegativeFloat.test(value);break;
			case "url" :
				valid = regex.url.test(value); break;
			case "QQ" : 
				valid = regex.QQ.test(value); break;
			case "address":
				valid = regex.address.test(value);break;
			case "postcode" :
				valid = regex.postcode.test(value); break;
			case "idCard" : 
				valid = regex.idCard.test(value); break;
			case "ip" : 
				valid = regex.ip.test(value); break;
			case "userValidate":
				valid = v.validate(value); break;
			default: 
				throw new Error("unknown validateType!"); break;
			}
			if(!valid && showDialog){
				_remind(v.remind, v.name + " is invalid!");
			}
		}
		return valid;
	}
	
	function _validateField(v, showDialog){
		var $field = $(_$root.find("#"+v.id));
		if($field.length==0){
			return true;
		}
		var value = $field.val();
		if("string" == typeof value){
			value = value.trim();
		}
		var mark = $field.siblings(".validate").remove();//去掉之前留下的验证标记
		//required check 不能为空字段的验证     &  regex 正则表达式验证
		if(_requiredValidate($field, value, v, showDialog) && 
				_regexValidate($field, value, v, showDialog)){
			if(!showDialog && v.asynValidate){
				v.asynValidate(value);
				$field.after(loading);
				return undefined;
			}
			if(!v.asynValidate){
				$field.after(validTip);
			}else {
				$field.after(mark);
			}
			return true;
		}else{
			$field.after(errTip);
			return false;
		}
	}
	
	function _validate(showDialog){
		var valid = true;
		$.each(config,function(i, v){
			valid = _validateField(v,showDialog)? valid : false;
			return valid;
		});
		return valid;
	}
	
	this.validate = function(showDialog){
		if(showDialog==undefined){
			return _validate(true);
		}
		return _validate(showDialog);
	};
	
	this.markInvalid = function(id){
		var $field = $(_$root.find("#"+id));
		$field.siblings(".validate").remove();
		$field.after(errTip);
	};
	
	this.markValid = function(id){
		var $field = $(_$root.find("#"+id));
		$field.siblings(".validate").remove();
		$field.after(validTip);
	};
	
	_init();
}
$.Validator = Validator;
$.Validator.regex = {
		email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*.\w+([-.]\w+)*$/,
		url : /^[a-zA-z]+:\/\/[^\s]*$/,
		userName : /^\w{4,16}$/,			//以字母数字或下划线组合长度在4-16位
		password : /(^\d+$)|(^[a-zA-Z]+$)|(^[\\~\\!%\\*\\.\\,\\^\\?\\$\\&_#@\\(\\)\\-\\+]+$)/,//不能为纯字母、数字、特殊3种符号,长度在6-16位之间
		productName : /^[a-zA-Z][a-zA-Z0-9_\s]{3,25}$/, //以字母开头的字母、数字、下划线和空格组合 长度在4到26位
		telephone : /^\d{3}-\d{8}|\d{4}-\d{7}$/,   //例如021-87888822
		cellphone : /^1\d{10}$/,					//手机号码 13011110000
		QQ : /[1-9][0-9]{4,}$/,
		postcode : /^[1-9]\d{5}(?!\d)$/,       //邮政编码
		address:/^\w{4,25}$/,
		idCard : /^\d{15}|\d{18}$/,				//15或18位数字
		ip : /^\d+.\d+.\d+.\d+$/,
		positiveInteger : /^[1-9]\d*$/,      //正整数
		positiveFloat : /^[1-9]\d*.\d*$|^0.\d*[1-9]$|^[1-9]\d*$/, //正浮点数
		nonNegativeInteger : /^[1-9]\d*|0$/,  //非负整数
		nonNegativeFloat : /^(0|[1-9]\d*)(\.\d*)?$/
};