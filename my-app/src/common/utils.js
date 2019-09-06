import locationMsg from './location'

function redirectToLogin(router) {
    router.push({
        name: 'login'
    })
}

//AccessToken
function doRequest(axios, store, router, config) {
    const requestPromise = new Promise((resolve, reject) => {
        axios(config).then((response) => {
            resolve(response)
        }).catch((error) => {

            if (error.response.status === 401) {
                refreshAccessToken(axios, store, router).then((response) => {
                    resolve(response)
                    axios(config).then((response) => {
                        resolve(response)
                    }).catch((error) => {
                        if (error.response.status === 401) {
                            redirectToLogin(router)
                            reject(error)
                        } else {
                            reject(error)
                        }
                    })

                }).catch((error) => {
                    reject(error)
                })
            } else {
                reject(error)
            }
        })
    })
    return requestPromise
}


function checkRouterAuth(authArray, store) {
    if (!authArray) {
        return false
    }
    if (!(authArray instanceof Array)) {
        return false
    }
    if (!store.state.userInfo || !store.state.userInfo.accessToken || !store.state.userInfo.authority) {
        return false
    }
    return authArray.includes(store.state.userInfo.authority)
}

function completeList(objectArray) {
    if (!objectArray) {
        objectArray = [];
    }
    if (objectArray.length == 0) {
        for (var i = 0; i < 10; i++) {
            objectArray.push([]);
        }
    } else {
        if (objectArray.length < 10) {
            let ilength = 10 - objectArray.length
            for (let i = 0; i < ilength; i++) {
                objectArray.push([]);
            }
        } else {
            if (objectArray.length % 10 != 0) {
                var ilength = 10 - objectArray.length % 10
                for (let i = 0; i < ilength; i++) {
                    objectArray.push([]);
                }
            }
        }
    }
    return objectArray;
}

function data_1(value1, value2) {
    if (value1 != "") {
        var d = new Date(value1);
        var year = d.getFullYear();
        var month = d.getMonth() + 7;
        var day = d.getDate() - 1;

        //month>12
        if (month > 12) {
            month = month - 12;
            year = year + 1;
            //console.log("month=" + month)
            //day=0
            if (day - 1 < 0) {
                month = month - 1;
                //7.1
                if (month == 0) {
                    month = 12
                    year = year - 1
                }
                //month
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    day = 30;
                } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    day = 31;
                } else if (month == 2) {
                    if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                }
            }
            //day>0,
            else {
                if (month == 2) {
                    //
                    if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
                        if (day > 29) {
                            day = 29;
                        }
                    } else {
                        if (day > 28) {
                            day = 28;
                        }
                    }
                }
            }
        } else {
            if (day - 1 < 0) {
                month = d.getMonth() + 6;
                if (month == 4 || month == 6 || month == 9 || month == 11) {
                    day = 30;
                } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                    day = 31;
                } else if (month == 2) {
                    if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
                        day = 29;
                    } else {
                        day = 28;
                    }
                }
            }
        }

        if (month < 10) {
            month = "0" + month;
        } else {
            month;
        }

        if (day < 10) {
            day = "0" + day;
        } else {
            day;
        }
        var date = "";
        date += year;
        date += "-";
        date += month;
        date += "-";
        date += day;
        value2 = date;
        return value2;
    } else if (value1 == "") {
        value2 = ""
        return value2;
    }

}

String.prototype.format = function () {
    if (arguments.length == 0) return this;
    for (var s = this, i = 0; i < arguments.length; i++)
        s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
    return s;
}

function checkUserName(value, obj) {
    let userName = /[0-9]+[a-zA-Z]+[0-9a-zA-Z]*|[a-zA-Z]+[0-9]+[0-9a-zA-Z]*/;
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (!userName.test(value)) {
        errorInf.errMsg = locationMsg.message.err_msg_004.format(obj);
        errorInf.hasError = true;
    }
    return errorInf;
}

function checkEmail(value, obj) {
    let email = /^([A-Za-z0-9_\-+.])+@([A-Za-z0-9_\-.])+\.([A-Za-z]{2,4})$/;
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (!email.test(value)) {
        errorInf.errMsg = locationMsg.message.err_msg_015.format(obj);
        errorInf.hasError = true;
    }
    return errorInf;
}

function checkNameLength(value, obj, length) {
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (value.length > length) {
        errorInf.errMsg = locationMsg.message.err_msg_014.format(obj, length);
        errorInf.hasError = true;
    }
    return errorInf;
}

function checkUserNameLength(value, obj, length) {
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (value.length > length) {
        errorInf.errMsg = locationMsg.message.err_msg_005.format(obj, length);
        errorInf.hasError = true;
    }
    return errorInf;
}

function checkDataLength(value, obj, length) {
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (value.length > length) {

        errorInf.hasError = true;
    }
    return errorInf;
}

function checkCodeLength(value, obj) {
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (value.length != 5 && value.length != "") {

        errorInf.hasError = true;
    }
    return errorInf;
}

function checkIdLength(value, obj) {
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (value.length != 8 && value.length != "") {

        errorInf.hasError = true;
    }
    return errorInf;
}

function checkLoginCodeLength(value, obj) {
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (value.length != 6) {

        errorInf.hasError = true;
    }
    return errorInf;
}

function checkIsNumber(value, obj) {
    let num = /^\d*$/;
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (!num.test(value)) {
        errorInf.errMsg = locationMsg.message.err_msg_008.format(obj);
        errorInf.hasError = true;
    }
    return errorInf;
}

function checkTelPhone(value, obj) {
    let num = /^\d*$/;
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (!num.test(value)) {
        errorInf.errMsg = locationMsg.message.err_msg_008.format(obj);
        errorInf.hasError = true;
    }

    // if (value.length != 11) {
    //     errorInf.errMsg = locationMsg.message.err_msg_009.format(obj, 11);
    //     errorInf.hasError = true;
    // }
    return errorInf;
}

function checkTelPhoneLength(value, obj) {
    let errorInf = {
        errMsg: "",
        hasError: false
    }
    if (value.length != 11) {
        errorInf.errMsg = locationMsg.message.err_msg_009.format(obj, 11);
        errorInf.hasError = true;
    }
    return errorInf;
}

// YYYYMMDD
function checkIsDate(value) {
    if (isNaN(value)) {
        return false
    }
    if (value.length != 8) {
        return false
    }
    let year = value.substring(0, 4)
    let month = value.substring(4, 6)
    let day = value.substring(6)
    if (isNaN(Date.parse(year + '-' + month + '-' + day))) {
        return false
    }
    if (year < 1900) {
        return false
    }
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
        if (day > 31) {
            return false
        }
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
        if (day > 30) {
            return false
        }
    } else if (month == 2) {
        if ((year % 400 == 0) || (year % 4 == 0) && (year % 100 != 0)) {
            //闰年
            if (day > 29) {
                return false
            }
        } else {
            //平年
            if (day > 28) {
                return false
            }
        }
    }


    let now = new Date()
    let date = new Date(year, month - 1, day)
    if (!date || date >= now) {
        return false
    }
    return true
}

function downloadCsv(content, fileName) {
    let blob = new Blob(['\uFEFF' + content], {
        type: 'text/plain;charset=utf-8;'
    });
    if (window.navigator.msSaveBlob) {
        window.navigator.msSaveBlob(blob, fileName);
        URL.revokeObjectURL(blob);
    } else {
        var aTag = document.createElement('a');
        aTag.download = fileName;
        aTag.href = URL.createObjectURL(blob);
        document.body.appendChild(aTag);
        aTag.click();
        setTimeout(function () {
            URL.revokeObjectURL(blob);
            document.body.removeChild(aTag);
        }, 100);
    }
}

function numberLength2(num) {
    let numStr = '';
    if (num < 10) {
        numStr = '0' + num.toString();
    } else {
        numStr = num.toString();
    }
    return numStr;
}

function checkCapital(value) {
    let capital = /[A-Z]/;

    if (capital.test(value)) {
        return true
    } else {
        return false
    }

}

export default {
    checkLoginCodeLength,
    checkIdLength,
    checkCodeLength,
    doRequest,
    checkRouterAuth,
    completeList,
    data_1,
    checkUserName,
    checkEmail,
    checkNameLength,
    checkUserNameLength,
    checkDataLength,
    checkIsNumber,
    checkIsDate,
    checkTelPhone,
    checkTelPhoneLength,
    downloadCsv,
    numberLength2,
    checkCapital
}