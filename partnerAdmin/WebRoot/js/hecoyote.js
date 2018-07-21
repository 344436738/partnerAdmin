// 绑定事件
function addEvent(obj,ev,fn)
{
  if(obj.attachEvent)
  {
    obj.attachEvent('on'+ev,fn)
  }
  else
  {
    obj.addEventListener(ev,fn,false);
  }
}
//通过id名获取元素
function getById(sName)
{
  var oDom=document.getElementById(sName);
  return oDom;
}
//获取非行间样式(只能获取简单样式，不能获取复合样式：background、font、border等等，如果要获取，调用时写成background-color这样，即使css写成复合样式也可以获取的到)
function getStyle(obj,name)
{
  if(obj.currentStyle)
  {
    return obj.currentStyle[name];
  }
  else
  {
    return getComputedStyle(obj,false)[name];
  }
}
// 获取包含类名(包含任意个类名)并删除数组中的空项
function getByInClass(oParent,sClass)
{
  var aEle=oParent.getElementsByTagName('*');
  var aResult=[];
  for(var i=0;i<aEle.length;i++)
  {
    var oldClass=aEle[i].className;
    var aResu=oldClass.split(' ');
    var aSclass=sClass.split(' ');
    clearAir(aResu);
    clearAir(aSclass);
    var curClass=0;
    for(var j=0;j<aResu.length;j++)
    {
      for(var k=0;k<aSclass.length;k++)
      {
        if(aResu[j]==aSclass[k])
        {
          curClass++;
        }
      }
    }
    if(curClass==aSclass.length)
    {
      aResult.push(aEle[i]);
    }
  }
  return aResult;
}
// 删除数组中的空项
function clearAir(arr)
{
  for(var i=0;i<arr.length;i++)
  {
    if(arr[i]==''||typeof arr[i]=='undefined')
    {
      arr.splice(i,1);
      i--;
    }
  }
}
/**
 * String对象 原型添加trim(字符串 前后去空格)
 * @returns
 */
String.prototype.trim = function(){
 return this.replace(/(^\s*)(\s*$)/g, "");
};
/***********************增加删除类*********************/
function addClass(obj,addClass)
{
  var oldClass=obj.className;
  var newClass=oldClass+' '+addClass;
  return newClass;
}
// 移除要移除类的所有相同名
function removeNameClass(obj,remoClass)
{
  var oldClass=obj.className;
  var aResu=oldClass.split(' ');
  var aRemo=remoClass.split(' ');
  for(var i=0;i<aResu.length;i++)
  {
    for(var j=0;j<aRemo.length;j++)
    {
      if(aResu[i]==aRemo[j])
      {
        aResu.splice(i,1);
        i--;
      }
    }
  }
  var newClass=aResu.join(' ');
  return newClass;
}
// 下拉列表框
function select(oSelSpan,ulId,selId)
{
  var oSelUl=document.getElementById(ulId);
  var oSel=document.getElementById(selId);
  var aOpt=oSel.getElementsByTagName('option');
  oSelUl.innerHTML='';
  oSelUl.className=removeNameClass(oSelUl,'none');
  for(var i=0;i<aOpt.length;i++)
  {
    var oSelLi=document.createElement('li');
    oSelLi.innerHTML=aOpt[i].text;
    oSelLi.className='selli'
    oSelUl.appendChild(oSelLi);
  }
  var aSelLi=oSelUl.getElementsByTagName('li');
  for(var i=0;i<aSelLi.length;i++)
  {
    aSelLi[i].index=i;
    aSelLi[i].onmouseover=function()
    {
      settingLi(this.index);
    }
    aSelLi[i].onclick=function()
    {
      settingLi(this.index);
      oSelSpan.innerHTML=this.innerHTML;
      oSelUl.className=addClass(oSelUl,'none');
      aOpt[this.index].selected=true;
      chasel(oSel);
    }
  }
  document.onclick=function(ev)
  {
    var oEvent=ev||event;
    var target=oEvent.srcElement||oEvent.target;
    if(target!=oSelSpan)
    {
      oSelUl.className=addClass(oSelUl,'none');
    }
    else
    {
      oSelUl.className=removeNameClass(oSelUl,'none');
    }
  }
  function chasel(obj)
  {
    if(document.all)
    {
      obj.fireEvent("onchange");
    }
    else
    {
      var evt=document.createEvent('HTMLEvents');
      evt.initEvent('change',true,true);
      obj.dispatchEvent(evt);
    }
  }
  function settingLi(cur)
  {
    for(var j=0;j<aSelLi.length;j++)
    {
      aSelLi[j].className='selli';
    }
    aSelLi[cur].className="actselli";
  }
}
//placeholder点击input里面的站位文字的显示隐藏
function placeHol(aPlaSp,aPlaInp,aPlaTxt)
{
  for(var i=0;i<aPlaSp.length;i++)
  {
    aPlaSp[i].index=i;
    aPlaInp[i].index=i;
    if(aPlaInp[i].value)
    {
      aPlaTxt[i].className=addClass(aPlaTxt[i],'none');
    }
    aPlaSp[i].onclick=function()
    {
      aPlaInp[this.index].focus;
      aPlaTxt[this.index].className=addClass(aPlaTxt[this.index],'none');
    };
    addEvent(aPlaInp[i],'blur',function()
    {
      if(this.value=='')
      {
        aPlaTxt[this.index].className=removeNameClass(aPlaTxt[this.index],'none');
      }
    });
  }
}
// 改变选择图片的状态
function chanImg(objImg,objFor)
{
  if(objFor.checked==true)
  {
    var str=getStyle(objImg,'backgroundImage').replace('no.png','se.png');
  }
  else
  {
    var str=getStyle(objImg,'backgroundImage').replace('se.png','no.png');
  }
  objImg.style.backgroundImage=str;
}
//复选框
function checkInp(aImgCh,aChec)
{
  for(var i=0;i<aImgCh.length;i++)
  {
    aImgCh[i].index=i;
    chanImg(aImgCh[i],aChec[i]);
    aImgCh[i].onclick=function()
    {
      if(aChec[this.index].checked==true)
      {
        aChec[this.index].checked=false;
      }
      else
      {
        aChec[this.index].checked=true;
      }
      chanImg(this,aChec[this.index]);
    }
  }
}
// 点击阴影的显示隐藏(多对一)
function cliSHMore(aBtn,oBox,oColse)
{
  for(var i=0;i<aBtn.length;i++)
  {
    aBtn[i].index=i;
    aBtn[i].onclick=function()
    {
      var shCur=this.index;
      oBox.className=removeNameClass(oBox,'none');
    }
    oColse.onclick=function()
    {
      oBox.className=addClass(oBox,'none');
    }
  }
}
// 点击阴影的显示隐藏(一对一)
function cliSH(aBtn,aBox,aColse)
{
  for(var i=0;i<aColse.length;i++)
  {
    aColse[i].index=i;
    aBtn[i].index=i;
    aBtn[i].onclick=function()
    {
      var shCur=this.index;
      for(var j=0;j<aColse.length;j++)
      {
        aBox[j].className=addClass(aBox[j],'none');
      }
      aBox[shCur].className=removeNameClass(aBox[shCur],'none');
    }
    aColse[i].onclick=function()
    {
      var clCur=this.index;
      aBox[clCur].className=addClass(aBox[clCur],'none');
    }
  }
}
function stopBubble(e)
{
  //如果提供了事件对象，则这是一个非IE浏览器
  if(e&&e.stopPropagation)
    e.stopPropagation();
  else
    window.event.cancelBubble = true;
  // return false;
}
/*if ( e && e.stopPropagation )
//因此它支持W3C的stopPropagation()方法
e.stopPropagation();
else
//否则，我们需要使用IE的方式来取消事件冒泡
window.event.cancelBubble = true;
return false;*/
//选项卡
function tabCon(aBtn,aCon)
{
  for(var i=0;i<aBtn.length;i++)
  {
    aBtn[i].index=i;
    aBtn[i].onclick=function()
    {
      for(var j=0;j<aBtn.length;j++)
      {
        aBtn[j].className=removeNameClass(aBtn[j],'acttabsp');
        aCon[j].className=addClass(aCon[j],'none');
      }
      this.className=addClass(this,'acttabsp');
      aCon[this.index].className=removeNameClass(aCon[this.index],'none');
    }
  }
}
// 选项卡，传入按钮增加移除类
function tabConClas(aBtn,sClass,aCon)
{
  for(var i=0;i<aBtn.length;i++)
  {
    aBtn[i].index=i;
    aBtn[i].onclick=function()
    {
      for(var j=0;j<aBtn.length;j++)
      {
        aBtn[j].className=removeNameClass(aBtn[j],sClass);
        aCon[j].className=addClass(aCon[j],'none');
      }
      this.className=addClass(this,sClass);
      aCon[this.index].className=removeNameClass(aCon[this.index],'none');
    }
  }
}
//单选按钮
function daraSdi(aImgRa,aRaRa)
{
  for(var i=0;i<aImgRa.length;i++)
  {
    aImgRa[i].index=i;
    chanImg(aImgRa[i],aRaRa[i]);
    aImgRa[i].onclick=function()
    {
      for(var j=0;j<aImgRa.length;j++)
      {
        aRaRa[j].checked=false;
        chanImg(aImgRa[j],aRaRa[j]);
      }
      aRaRa[this.index].checked=true;
      chanImg(aImgRa[this.index],aRaRa[this.index]);
    }
  }
}
// 含全选复选框
function cheAllCon(oAllSp,oAllInp,aSonSp,aSonInp,numb)
{
  numb.innerHTML=0;
  for(var i=0;i<aSonInp.length;i++)
  {
    aSonSp[i].index=i;
    if(aSonInp[i].checked==true)
    {
      numb.innerHTML=parseInt(numb.innerHTML)+1;
    }
    else
    {
      numb.innerHTML=parseInt(numb.innerHTML);
    }
    chanImg(aSonSp[i],aSonInp[i]);
    aSonSp[i].onclick=function()
    {
      var cur=this.index;
      if(aSonInp[cur].checked==true)
      {
        numb.innerHTML=parseInt(numb.innerHTML)-1;
        aSonInp[cur].checked=false;
        chanImg(aSonSp[cur],aSonInp[cur]);
        if(parseInt(numb.innerHTML)<aSonInp.length)
        {
          oAllInp.checked=false;
          chanImg(oAllSp,oAllInp);
        }
      }
      else
      {
        numb.innerHTML=parseInt(numb.innerHTML)+1;
        aSonInp[cur].checked=true;
        chanImg(aSonSp[cur],aSonInp[cur]);
        if(parseInt(numb.innerHTML)<aSonInp.length)
        {
          oAllInp.checked=false;
          chanImg(oAllSp,oAllInp);
        }
        else if(parseInt(numb.innerHTML)==aSonInp.length)
        {
          oAllInp.checked=true;
          chanImg(oAllSp,oAllInp);
        }
      }
    }
  }
  oAllSp.onclick=function()
  {
    if(oAllInp.checked==true)
    {
      oAllInp.checked=false;
      chanImg(oAllSp,oAllInp);
      numb.innerHTML=0;
      for(var j=0;j<aSonSp.length;j++)
      {
        aSonInp[j].checked=false;
        chanImg(aSonSp[j],aSonInp[j]);
      }
    }
    else
    {
      oAllInp.checked=true;
      chanImg(oAllSp,oAllInp);
      numb.innerHTML=aSonSp.length;
      for(var j=0;j<aSonSp.length;j++)
      {
        aSonInp[j].checked=true;
        chanImg(aSonSp[j],aSonInp[j]);
      }
    }
  }
}
// 上传图片并预览
function previewImage(aImg,aFile)
{
  for(var i=0;i<aImg.length;i++)
  {
    aFile[i].index=i;
    aFile[i].onchange=function()
    {
      var curFil=this.index;
      if (aFile[curFil].files)
      {
        var reader = new FileReader();
        reader.readAsDataURL(aFile[curFil].files[0]);
        reader.onload = function(e)
        {
          aImg[curFil].src=reader.result;
        }
      }
      else
      {
        aFile[curFil].select();
        var srcImg = document.selection.createRange().text;
        aImg[curFil].src=srcImg;
      }
    };
  };
}
