$(function(){
    var layer;
    layui.use('layer', function(){
        layer = parent.layer === undefined ? layui.layer : parent.layer;
    });

	var tab = 'account_number';
	// 选项卡切换
	$(".account_number").click(function () {
		$('.tel-warn').addClass('hide');
		tab = $(this).attr('class').split(' ')[0];
		checkBtn();
        $(this).addClass("on");
        $(".message").removeClass("on");
        $(".form2").addClass("hide");
        $(".form1").removeClass("hide");
    });
	// 选项卡切换
	$(".message").click(function () {
		$('.tel-warn').addClass('hide');
		tab = $(this).attr('class').split(' ')[0];
		checkBtn();
		$(this).addClass("on");
        $(".account_number").removeClass("on");
		$(".form2").removeClass("hide");
		$(".form1").addClass("hide");
		$('.code2').find('img').attr('src','/user/verifyCode?'+Math.random()).click(function(event) {
        	$(this).attr('src', '/user/verifyCode?'+Math.random());
        });
    });

	$('#num').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#pass').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#veri').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#veri2').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#num2').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	$('#veri-code').keyup(function(event) {
		$('.tel-warn').addClass('hide');
		checkBtn();
	});

	// 按钮是否可点击
	function checkBtn()
	{
		$(".log-btn").off('click');
		if (tab == 'account_number') {
			var inp = $.trim($('#num').val());
			var pass = $.trim($('#pass').val());
			if (inp != '' && pass != '') {
				if (!$('.code').hasClass('hide')) {
					code = $.trim($('#veri').val());
					if (code == '') {
						$(".log-btn").addClass("off");
					} else {
						$(".log-btn").removeClass("off");
						sendBtn();
					}
				} else {
					$(".log-btn").removeClass("off");
						sendBtn();
				}
			} else {
				$(".log-btn").addClass("off");
			}
		} else {
			var phone = $.trim($('#num2').val());
			var code2 = $.trim($('#veri-code').val());
			var code3 = $.trim($('#veri2').val());
			if (phone != '' && code2 != '' && code3 != '') {
				$(".log-btn").removeClass("off");
				sendBtn();
			} else {
				$(".log-btn").addClass("off");
			}
		}
	}

	function checkAccount(username){
		if (username == '') {
			$('.num-err').removeClass('hide').find("em").text('请输入账户');
			return false;
		} else {
			$('.num-err').addClass('hide');
			return true;
		}
	}

	function checkPass(pass){
		if (pass == '') {
			$('.pass-err').removeClass('hide').text('请输入密码');
			return false;
		} else {
			$('.pass-err').addClass('hide');
			return true;
		}
	}

	function checkCode(code){
		if (code == '') {
			return false;
		} else {
			return true;
		}
	}

	function checkPhone(phone){
		var status = true;
		if (phone == '') {
			$('.num2-err').removeClass('hide').find("em").text('请输入手机号');
			return false;
		}
		var param = /^1[345678]\d{9}$/;
		if (!param.test(phone)) {
			// globalTip({'msg':'手机号不合法，请重新输入','setTime':3});
			$('.num2-err').removeClass('hide');
			$('.num2-err').text('手机号不合法，请重新输入');
			return false;
		}

		return status;
	}

	function checkPhoneCode(pCode){
		if (pCode == '') {
			$('.error').removeClass('hide').text('请输入验证码');
			return false;
		} else {
			$('.error').addClass('hide');
			return true;
		}
	}

	// 登录点击事件
	function sendBtn(){
		if (tab == 'account_number') {
			$(".log-btn").click(function(){
				var inp = $.trim($('#num').val());
				var pass = $.md5($.trim($('#pass').val()));
				if (checkAccount(inp) && checkPass(pass)) {
					var ldata = {telphone:inp,otpCode:'',password:pass,type:1};
					if (!$('.code').hasClass('hide')) {
						code = $.trim($('#veri').val());
						if (!checkCode(code)) {
							return false;
						}
						ldata.code = code;
					}
					$.ajax({
			            url: '/user/login',
			            type: 'post',
			            data: ldata,
			            success:function(data){
                            if(data.code == '-1'){
                                $(".log-btn").off('click').addClass("off");
                                $('.num-err').removeClass('hide').find('em').text(data.data.errMsg);
                                $('.num-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
                                return false;
                            } else if (data.code == '0') {
                                layer.msg(data.msg,{icon:1});
                                setTimeout(function () {
                                    window.location.href = "/index";
                                }, 2000);
			                } else if(data.code == '2') {
			                	$(".log-btn").off('click').addClass("off");
			                    $('.pass-err').removeClass('hide').find('em').text(data.msg);
			                    $('.pass-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
			                    $('.code').removeClass('hide');
			                    $('.code').find('img').attr('src','/user/verifyCode?'+Math.random()).click(function(event) {
			                    	$(this).attr('src', '/user/verifyCode?'+Math.random());
			                    });;
			                    return false;
			                } else if(data.code == '3') {
			                	$(".log-btn").off('click').addClass("off");
			                    $('.img-err').removeClass('hide').find('em').text(data.msg);
			                    $('.img-err').find('i').attr('class', 'icon-warn').css("color","#d9585b");
			                    $('.code').removeClass('hide');
			                    $('.code').find('img').attr('src','/user/verifyCode?'+Math.random()).click(function(event) {
			                    	$(this).attr('src', '/user/verifyCode?'+Math.random());
			                    });
			                    return false;
			                }
			            },
			            error:function(){
			                
			            }
			        });
				} else {
					return false;
				}
			});
		} else {
			$(".log-btn").click(function(){
				var phone = $.trim($('#num2').val());
				var pcode = $.trim($('#veri-code').val());
                var ldata = {telphone:phone,otpCode:pcode,password:'',type:2};
				if (checkPhone(phone) && checkPhoneCode(pcode)) {
					$.ajax({
			            url: '/user/login',
			            type: 'post',
			            data: ldata,
			            success:function(data){
			                if (data.code == '0') {
                                layer.msg(data.msg,{icon:1});
                                setTimeout(function () {
                                    window.location.href = "/index";
                                }, 2000);
			                } else if(data.code == '-1') {
			                	$(".log-btn").off('click').addClass("off");
			                    $('.num2-err').removeClass('hide').text(data.data.errMsg);
			                    return false;
			                }
			            },
			            error:function(){
			                
			            }
			        });
				} else {
					$(".log-btn").off('click').addClass("off");
					// $('.tel-warn').removeClass('hide').text('登录失败');
					return false;
				}
			});
		}
	}

	// 登录的回车事件
	$(window).keydown(function(event) {
    	if (event.keyCode == 13) {
    		$('.log-btn').trigger('click');
    	}
    });


	$(".form-data").delegate(".send","click",function () {
		var phone = $.trim($('#num2').val());
		var note = $('#note').val();
		var code = $('#veri2').val();
		if (code == '') {
			globalTip({'msg':'请输入图形验证码!','setTime':3});
			return false;
		}

		// 发送验证码
        $.ajax({
            url: '/user/getotp',
            type: 'post',
            async: true,
            data: {'telphone':phone},
            success:function(data){
                if (data.code == '0') {
                    var oTime = $(".form-data .time"),
                        oSend = $(".form-data .send"),
                        num = parseInt(oTime.text()),
                        oEm = $(".form-data .time em");
                    $(".form-data .send").hide();
                    oTime.removeClass("hide");
                    var timer = setInterval(function () {
                        var num2 = num-=1;
                        oEm.text(num2);
                        if(num2==0){
                            clearInterval(timer);
                            oSend.text("重新发送验证码");
                            oSend.show();
                            oEm.text("120");
                            oTime.addClass("hide");
                        }
                    },1000);
                } else if(data.code == '1'){
                    globalTip({'msg':'验证码已发送!','setTime':3});
                } else {
                    globalTip({'msg':'图形验证码错误!','setTime':3});
                    $('.code2').find('img').attr('src','/user/verifyCode?'+Math.random())
                }
            },
            error:function(){
                globalTip({'msg':'验证码发送失败!','setTime':3});
            }
        });
    });



});