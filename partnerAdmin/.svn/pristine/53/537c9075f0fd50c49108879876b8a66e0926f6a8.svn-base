function ghbstable(alltr,ocolor,jcolor)
{
  for(var i=0;i<alltr.length;i++)
  {
    alltr[i].index=i;
    if(i%2==0)
    {
      alltr[i].style.backgroundColor=jcolor;
    }
    else
    {
      alltr[i].style.backgroundColor=ocolor;
    }
  }
}
//复选框
function moCheFun(aImg,aChck)
{
  for(var i=0;i<aImg.length;i++)
  {
    aChck[i].index=i;
    aImg[i].index=i;
    aImg[i].onclick=function()
    {
      if(aChck[this.index].checked==true)
      {
        aChck[this.index].checked=false;
        this.className=removeNameClass(this,'sele');
//        removeClass(this,this.className,'sele');
      }
      else
      {
        aChck[this.index].checked=true;
        this.className=addClass(this,'sele');
//        addClass(this,this.className,'sele');
      }
    }
  }
}
//复选框包含全选与全不选
function moCheFunAll(aImg,aChck)
{
  for(var i=0;i<aImg.length;i++)
  {
    aChck[i].index=i;
    aImg[i].index=i;
    aImg[i].onclick=function()
    {
      if(aChck[this.index].checked==true)
      {
        if(this.index==0)
        {
          for(var j=0;j<aImg.length;j++)
          {
            aImg[j].className='checkspan';
            aChck[j].checked=false;
          }
        }
        else
        {
          aChck[this.index].checked=false;
          this.className=removeNameClass(this,'sele');
//          removeclass(this,this.className,'sele');
        }
      }
      else
      {
        if(this.index==0)
        {
          for(var j=0;j<aImg.length;j++)
          {
            aImg[j].className='checkspan sele';
            aChck[j].checked=true;
          }
        }
        else
        {
          aChck[this.index].checked=true;
          this.className=addClass(this,'sele');
//          addClass(this,this.className,'sele');
        }
      }
    }
  }
}

//通过类名获取元素
function getByClass(oParent,sClass)
{
  var aResult=[];
  var aEle=oParent.getElementsByTagName('*');
  for(i=0;i<aEle.length;i++)
  {
    if(aEle[i].className==sClass)
    {
      aResult.push(aEle[i]);
    }
  }
  return aResult;
}
// 获取包含类名
function getByIncluClass(oParent,sClass)
{
  var aResult=[];
  var aEle=oParent.getElementsByTagName('*');
  for(i=0;i<aEle.length;i++)
  {
    if(aEle[i].className.search('radi')!=-1)
    {
      aResult.push(aEle[i]);
    }
  }
  return aResult;
}
// 单选按钮
/*function daradi(aImgRa,aRaRa)
{
  for(var i=0;i<aImgRa.length;i++)
  {
    aImgRa[i].index=i;
    aRaRa[i].index=i;
    aImgRa[i].onclick=function()
    {
      for(var j=0;j<aImgRa.length;j++)
      {
        aImgRa[j].className="radi";
        aRaRa[j].checked=false;
      }
      aRaRa[this.index].checked=true;
      addClass(this,this.className,'rasele');
    }
  }
}*/
// 使用span下拉框
function seSp(oSpan,oSele,aSelOpt)
{
  oSele.onchange=function()
  {
    for(var i=0;i<aSelOpt.length;i++)
    {
      if(oSele.value==aSelOpt[i].value)
      {
        oSpan.innerHTML=aSelOpt[i].innerHTML;
      }
    }
  }
}
/*事件绑定*/
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

function getByInClass(oParent,sClass)
{
  var aEle=oParent.getElementsByTagName('*');
  var aResult=[];
  for(var i=0;i<aEle.length;i++)
  {
    var oldClass=aEle[i].className;
    var aResu=oldClass.split(' ');
    for(var j=0;j<aResu.length;j++)
    {
      if(aResu[j]==sClass)
      {
        aResult.push(aEle[i]);
      }
    }
  }
  return aResult;
}
//增加类
function addClass(obj,addClass)
{
  var oldClass=obj.className;
  var newClass=oldClass+' '+addClass;
  return newClass;
}
//移除要移除类的所有相同名
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