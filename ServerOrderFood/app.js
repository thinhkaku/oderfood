var express = require("express");
var mysql = require('mysql');
var app = express();
app.use(express.static(__dirname + '/'));
var server = require("http").createServer(app);
var io = require("socket.io").listen(server);
var fs = require("fs");
server.listen(process.env.PORT || 3000);

var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "banhangonline"
});

io.sockets.on('connection', function (socket) {

  console.log("NOTICE: NEW USER CONNECTED! " + socket.id);

  socket.on('CLIENT_SEND_REQUEST_LOGIN', function(data){
  	var userAndPass = data;
    socket.un = data;
    console.log(socket.un);
  	checkUserAndPass(userAndPass,socket);
  });

   socket.on('REQUEST_BOOK', function(data){
	 getListTable(data,socket);
  });

   socket.on('CLIENT_SEND_TABLE_DA_DAT',function(data){

   });

   socket.on('CLIENT_SEND_CHECK_TABLE', function(data){
    getClientSendCheckTable(socket,data);
   });

   socket.on('REQUEST_TABLE_TRONG',function(data){
      getListBanTrong(socket);
   });

   socket.on('REQUEST_TABLE_DA_DAT',function(data){
      getListBanDaDat(socket);
   });

   socket.on('REQUEST_TINH_TRANG',function(data){
       ktTinhTranBan(socket);

   });

    socket.on('LOG_OUT', function(data){
  	logOut(data,socket);
  });

    socket.on('CLIENT_SEND_REQUEST_DRINNK', function(data){
  	  getMenuDrink(socket);
  });

    socket.on('CLIENT_SEND_REQUEST_FOOD', function(data){
      getMenuFood(socket);
  });

    socket.on('CLIENT_SEND_MENU', function(data){
      getMenu(socket);
    });

    socket.on('CLIENT_SEND_DELETE_MENU',function(data){
      console.log(data);
      deleteMenu(socket,data);
    });

    socket.on('CLIENT_SEND_TEMP_BILL', function(data){
      //console.log(data);
      insertTempBill(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_BILL', function(data){
      //console.log(data);
      getBillExists(socket,data);
  });

    socket.on('DELETE_BILL', function(data){
      //console.log(data);
      deleteBill(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_LIST_BILL', function(data){
      //console.log(data);
      getListBill(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_LIST_ITEM_BILL', function(data){
      //console.log(data);
      getListItemBill(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_LIST_STAFF', function(data){
      //console.log(data);
      getListStaff(socket);
  });
    socket.on('CLIENT_REQUEST_TINH_TRANG_BAN',function(data){
      console.log(data);
        editTinhTrang(socket,data);
    });

    socket.on('CLIENT_REQUEST_ADD_MENU',function(data){
      console.log(data);
      addMenu(socket,data);
    });

    socket.on('CLIENT_SEND_ALL_TEMP_BILL',function(data){
      console.log(data);
      getListAllBill(socket);
    });


    socket.on('CLIENT_SEND_IMAGE_STAFF', function(data){
      //console.log(data);
      serverSendResultUpImage(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_INSERT_STAFF', function(data){
      //console.log(data);
      insertStaff(socket,data);
  });

    socket.on('CLIENT_SEND_REQUEST_DELETE_STAFF', function(data){
      //console.log(data);
      deleteStaff(socket,data);
  });
    socket.on('CLIENT_SEND_NEWS',function(data){
      console.log(data);
      getNews(socket);
    });

     socket.on('CLIEND_REQUEST_DANG_TIN',function(data){
          insertBangTin(socket,data);
      });
    socket.on('CLIENT_SEND_REQUEST_EDIT_STAFF', function(data){
      //console.log(data);
      editStaff(socket,data);
      console.log(data);

  });

  

});


function getClientSendCheckTable(socket, data){
     con.query("UPDATE `danhsachban` SET tinhTrang = 1 WHERE tenBan="+data,function(err,result,fields){
      if (err) {
        console.log(err);
      }else{
        result=JSON.stringify(result);
        result=result.substr(118,1);
        console.log(result);
          socket.emit('SEVER_SEND_TINH_TRANG',result);
      }
     });
}


function getListBanDaDat(socket){
   con.query("SELECT * FROM `danhsachban` WHERE tinhTrang !=0",function(err,result,fields){
        if (err) {
          console.log(err);
        }else{
           io.sockets.emit('RESULT_TABLE_DA_DAT',result);
        }
   });
}


function getListBanTrong(socket){
     con.query("SELECT * FROM `danhsachban` WHERE tinhTrang = 0",function(err,result,fields){
      if (err) {
        console.log(err);
      }else{
        io.sockets.emit('RESULT_TABLE_TRONG',result);
      }
     });
}

function insertBangTin(socket,data){
    con.query(data,function(err,result,fields){
      if (err) {
        console.log(err);
      }else{
          socket.emit('SERVER_REQUEST_RESULT_DANGTIN',"1");
      }
    });
}

function getNews(socket){
     con.query("SELECT * FROM bangtin",function(err,result,fields){
      if (err) {
        console.log(err);
      }else{
        io.sockets.emit('SEVER_SEND_NEWS',result);
      }
     });
}

function editTinhTrang(socket,data){
    con.query("UPDATE `danhsachban` SET `tinhTrang` = 3 WHERE `tenBan`="+data,function(err,result,fields){
       if (err) {
        console.log(err);
       }else{
           con.query("SELECT * FROM danhsachban", function (err, result, fields) {
    if (err) throw err;
      io.sockets.emit('SERVER_SEND_LIST_TABLE',result);
      //console.log(result);
    });
       }
    });
}

function duaX(x){
  if (x!="") {
                
              con.query("SELECT COUNT(*) FROM `hoadonchothanhtoan` WHERE `tenBan`='"+x+"' AND `tinhTrangOder`=0",function(err,result,fields){
              if (err) {
                console.log(err);
              }else{
                y=JSON.stringify(result);
                y=y.substr(13,1);
                if (y!=0) {
                   con.query("UPDATE `danhsachban` SET `tinhTrang` = 2 WHERE `tenBan`='"+x+"'",function(err,result,fields){
                      if (err) {
                        console.log(err);
                      }else{
                        con.query("SELECT * FROM danhsachban", function (err, result, fields) {
    if (err) throw err;
      io.sockets.emit('SERVER_SEND_LIST_TABLE',result);
      //console.log(result);
    });
                      }
                   });
                }else{
                  con.query("UPDATE `danhsachban` SET `tinhTrang` = 1 WHERE `tenBan`='"+x+"'",function(err,result,fields){
                      if (err) {
                        console.log(err);
                      }else{
                        con.query("SELECT * FROM danhsachban", function (err, result, fields) {
    if (err) throw err;
      io.sockets.emit('SERVER_SEND_LIST_TABLE',result);
      //console.log(result);
    });
                      }
                   });
                }
                
              }
            });

            }
}

function ktTinhTranBan(socket){
  var idHoaDonCucBo ="";
  var n=10;
  var x="";
        var m=1;
        var l=12;
        var y="";
  con.query("SELECT tenBan FROM danhsachban WHERE tinhTrang!=0",function(err,result,fields){
    if (err) {
      console.log(err);
    }else{
      idHoaDonCucBo=JSON.stringify(result);
        idHoaDonCucBo=idHoaDonCucBo.substring(1,idHoaDonCucBo.length-1);
        for (var i = 0; i <= 50; i++) {
            x=idHoaDonCucBo.substr(n,m);
            duaX(x);
              n=m+n+l;
        }


       
    }
  });
 
}

function getListAllBill(socket){
  con.query("SELECT * FROM `hoadonchothanhtoan`",function(err,result,fields){
     if (err) {
      
     }else{
      io.sockets.emit('SEVER_SEND_ALL_REQUEST_BILL',result);
     }
  });
}

function addMenu(socket,data){
   con.query(data,function(err,result,fields){
    if (err) throw err;
     socket.emit('SEVER_SEND_RESULT_ADD_MENU',"1");
     con.query("SELECT * FROM danhsachmonan",function(err,result,fields){
                          if (err) throw err;
                          io.sockets.emit('SERVER_SEND_MENU',result);
                          
                        });
   });
}

function deleteMenu(socket, data)
{
  con.query("DELETE FROM `dsmonantheohd` WHERE `tenMonAn` = '"+data+"'",function(err, result,fields){
    if (err) {
      socket.emit('SEVER_SEND_RESULT_DELETE_MENU_MANAGER',"false");
    }else{
        con.query("DELETE FROM `hoadonchothanhtoan` WHERE `tenMonAn` = '"+data+"'",function(err,result,fields){
          if (err) {
            socket.emit('SEVER_SEND_RESULT_DELETE_MENU_MANAGER',"false");
          }else{
               con.query("DELETE FROM `danhsachmonan` WHERE `tenMonAn`='"+data+"'",function(err,result,fields){
                    if (err) {
                      socket.emit('SEVER_SEND_RESULT_DELETE_MENU_MANAGER',"false");
                    }else{
                        con.query("SELECT * FROM danhsachmonan",function(err,result,fields){
                          if (err) throw err;
                          io.sockets.emit('SERVER_SEND_MENU',result);
                          getMenuFood(socket);
                          getMenuDrink(socket);
                        });
                    }
               });
          }
        });
    }
  });
}

function checkUserAndPass(userPass,socket)
{
  con.query("SELECT * FROM nhanvien WHERE userPass = '"+userPass+"'", function (err, result, fields) {
    if (err) throw err;
    socket.emit('SERVER_SEND_RESULT',result);
    console.log(result);
    if (result.length == 1) {
    	con.query("UPDATE nhanvien SET online = 1 WHERE userPass = '"+userPass+"'", function (err, result, fields) {
    	if (err) throw err;});
    }
  });
	//console.log(userPass);
}

function getListTable(num,socket)
{

  if (num == -1) {
      con.query("SELECT * FROM danhsachban", function (err, result, fields) {
    if (err) throw err;
    io.sockets.emit('SERVER_SEND_LIST_TABLE',result);
    //console.log(result);
  });
  }
  else
  {
      con.query("UPDATE danhsachban SET tinhTrang = 1 WHERE tenBan = '"+num+"'", function (err, result, fields) {
      if (err) throw err;
      //console.log(result);
      });

      con.query("SELECT * FROM danhsachban", function (err, result, fields) {
      if (err) throw err;
      io.sockets.emit('SERVER_SEND_LIST_TABLE',result);
      //console.log(result);
      });
  }
}

function logOut(idStaff,socket)
{
	con.query("UPDATE nhanvien SET online = 0 WHERE idNhanVien = '"+idStaff+"'", function (err, result, fields) {
    if (err) throw err;
    //console.log(result);
  });

  con.query("SELECT * FROM `nhanvien`", function (err, result, fields) {
    if (err) throw err;
      io.sockets.emit('SERVER_SEND_LIST_STAFF',result);
      //console.log(result);
  });
}

function getMenuDrink(socket)
{
	con.query("SELECT nhommonan.tenNhom, danhsachmonan.tenMonAn, danhsachmonan.gia, danhsachmonan.tenDVTinh"+
		", danhsachmonan.tinhTrang, danhsachmonan.anhMonAn "+
		"FROM nhommonan INNER JOIN danhsachmonan ON nhommonan.tenNhom = danhsachmonan.tenNhom WHERE nhommonan.loai = 'drink'", function (err, result, fields) {
    if (err) throw err;
    socket.emit('SERVER_SEND_MENU_DRINK',result);
    //console.log(result);
  });
}

function getMenu(socket)
{
  con.query("SELECT nhommonan.tenNhom, danhsachmonan.tenMonAn, danhsachmonan.gia, danhsachmonan.tenDVTinh"+
    ", danhsachmonan.tinhTrang, danhsachmonan.anhMonAn "+
    "FROM nhommonan INNER JOIN danhsachmonan ON nhommonan.tenNhom = danhsachmonan.tenNhom", function (err, result, fields) {
    if (err) throw err;
    socket.emit('SERVER_SEND_MENU',result);
    //console.log(result);
  });
}

function getMenuFood(socket)
{
  con.query("SELECT nhommonan.tenNhom, danhsachmonan.tenMonAn, danhsachmonan.gia, danhsachmonan.tenDVTinh"+
    ", danhsachmonan.tinhTrang, danhsachmonan.anhMonAn "+
    "FROM nhommonan INNER JOIN danhsachmonan ON nhommonan.tenNhom = danhsachmonan.tenNhom WHERE nhommonan.loai = 'food'", function (err, result, fields) {
    if (err) throw err;
    socket.emit('SERVER_SEND_MENU_FOOD',result);
    //console.log(result);
  });
}

function insertTempBill(socket,que)
{
    var arrQue = [];
    arrQue = que.split(";");
    for(var i=0; i<arrQue.length; i++)
    {
      con.query(arrQue[i], function (err, result, fields) {
      if (err) throw err;
      });
    }

}

function getBillExists(socket,table)
{
  con.query("SELECT * FROM `hoadonchothanhtoan` INNER JOIN `danhsachmonan` ON hoadonchothanhtoan.tenMonAn = danhsachmonan.tenMonAn WHERE `tenBan` = "+table, function (err, result, fields) {
  if (err) throw err;
  socket.emit("SERVER_SEND_BILL",result);
  //console.log(result);
  });
  
}

function deleteBill(socket,que)
{
    var arr = [];
    arr = que.split(";");
    for(var i=0; i<arr.length; i++)
    {
      con.query(arr[i], function (err, result, fields) {
      if (err) throw err;
      //console.log(result);
      });
    }

    con.query("SELECT * FROM danhsachban", function (err, result, fields) {
    if (err) throw err;
      io.sockets.emit('SERVER_SEND_LIST_TABLE',result);
      //console.log(result);
    });
}

function getListBill(socket,idStaff)
{
    con.query("SELECT * FROM `thongkehoadon` WHERE idNhanVien = '"+idStaff+"'", function (err, result, fields) {
    if (err) throw err;
      socket.emit('SERVER_SEND_LIST_BILL',result);
      //console.log(result);
    });
}

function getListItemBill(socket,idBill)
{
    con.query("SELECT * FROM `dsmonantheohd` WHERE idHoaDon = '"+idBill+"'", function (err, result, fields) {
    if (err) throw err;
      socket.emit('SERVER_SEND_LIST_ITEM_BILL',result);
      //console.log(result);
    });
}

function getListStaff(socket)
{
    con.query("SELECT * FROM `nhanvien`", function (err, result, fields) {
    if (err) throw err;
      io.sockets.emit('SERVER_SEND_LIST_STAFF',result);
      //console.log(result);
    });
}

function getFileNameImage(id)
{
  return "image/staff/" + id.substring(2) + getMilis() + ".png";
}


function getMilis()
{
  var date = new Date();
  var milis = date.getTime();
  return milis;
}

function serverSendResultUpImage(socket,data)
{
  var url = getFileNameImage(socket.id);
  fs.writeFile(url, data, function(err) {
    if(err) {
        return console.log(err);
    }
    socket.emit('SERVER_SEND_RESULT_UP_IMAGE',"/"+url);
  });
}

function insertStaff(socket,que)
{
    con.query(que, function (err, result, fields) {
    if (err) {
      console.log(err);
    }
    else{
      io.sockets.emit('SERVER_SEND_RESULT_INSERT_STAFF',"1");
      console.log(result);
    }

    });
}

function deleteStaff(socket,que)
{
  var idHoaDonCucBo="";
    con.query("SELECT `idHoaDon` FROM `thongkehoadon` WHERE idNhanVien = '"+que+"'",function(err,result,fields){
      if (err) {
          socket.emit('SERVER_SEND_RESULT_DELETE_STAFF',"false");
          console.log(err);
      }else
      {
        idHoaDonCucBo=JSON.stringify(result);
        idHoaDonCucBo=idHoaDonCucBo.substring(1,idHoaDonCucBo.length-1);
        console.log(idHoaDonCucBo);
        var n=13;
        var m=19;
        var l=16;
        var x="";
        for (var i = 0; i <= 50; i++) {
            x=idHoaDonCucBo.substr(n,m);
            con.query("DELETE FROM `dsmonantheohd` WHERE idHoaDon='"+x+"'",function(err,result,fields){
                if (err) {
                  socket.emit('SERVER_SEND_RESULT_DELETE_STAFF',"false");
                }
            });
              n=m+n+l;
        }

        con.query("DELETE FROM `thongkehoadon` WHERE idNhanVien='"+que+"'",function(err,result,fields){
            if (err) {
              socket.emit('SERVER_SEND_RESULT_DELETE_STAFF',"false");
            }else{
              con.query("DELETE FROM `nhanvien` WHERE idNhanVien='"+que+"'",function(err,result,fields){
                if (err) {
                  socket.emit('SERVER_SEND_RESULT_DELETE_STAFF',"false");
                }else{
                  con.query("SELECT * FROM `nhanvien`",function(err, resultt,fields){
            if (err) throw err;
               io.sockets.emit('SERVER_SEND_LIST_STAFF',resultt);
          });
                }
              });
              }
            });
            } 
        });
}




function editStaff(socket,que)
{
    con.query(que, function (err, result, fields) {
    if (err) {
      socket.emit('SERVER_SEND_RESULT_EDIT_STAFF',"false");
      console.log(err);
    }
    else{
        con.query("SELECT * FROM `nhanvien`", function (err, result, fields) {
        if (err) throw err;
          io.sockets.emit('SERVER_SEND_LIST_STAFF',result);
          //console.log(result);
        });
    }
    });
}




 // function stringFromUTF8Array(data)
 //  {
 //    const extraByteMap = [ 1, 1, 1, 1, 2, 2, 3, 0 ];
 //    var count = data.length;
 //    var str = "";
    
 //    for (var index = 0;index < count;)
 //    {
 //      var ch = data[index++];
 //      if (ch & 0x80)
 //      {
 //        var extra = extraByteMap[(ch >> 3) & 0x07];
 //        if (!(ch & 0x40) || !extra || ((index + extra) > count))
 //          return null;
        
 //        ch = ch & (0x3F >> extra);
 //        for (;extra > 0;extra -= 1)
 //        {
 //          var chx = data[index++];
 //          if ((chx & 0xC0) != 0x80)
 //            return null;
          
 //          ch = (ch << 6) | (chx & 0x3F);
 //        }
 //      }
      
 //      str += String.fromCharCode(ch);
 //    }
    
 //    return str;
 //  }


