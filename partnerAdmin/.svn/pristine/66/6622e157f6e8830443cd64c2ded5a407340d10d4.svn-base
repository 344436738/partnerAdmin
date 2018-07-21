<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<script type="text/javascript">
		function displayChildren(){
			if (isNull(img)) return;
			var tr = getTrElement(img);	  
		    var isToOpen = img.getAttribute('isOpen') == 'false';
		    var isByAjax = img.getAttribute('isAjax') == 'true';
		    var nodeID = img.getAttribute('id');
			if (!isNull(img) && img.getAttribute('isOpen') != null){
				if (img.getAttribute('isOpen') == 'false'){
					img.setAttribute('isOpen', 'true');
		            img.setAttribute('src', basePath+'<%=request.getContextPath() %>/image/minus.gif');
				}else{
		            img.setAttribute('isOpen', 'false');
		            img.setAttribute('src', basePath+'<%=request.getContextPath() %>/image/plus.gif');
				}
			}
		  
		    if (isToOpen && isByAjax)
		    {
		        var div = document.createElement('div');
		        div.innerHTML = "<img align='absmiddle' border='0' src='"+basePath+"<%=request.getContextPath() %>/image/loading.gif' /> 数据加载中，请稍候...";
		        img.parentNode.appendChild(div);
		        $(div).addClass('loading');
		        loadingChannels(tr, img, div, nodeID);
		    }
		    else
		    {
		        var level = getTreeLevel(tr);		    	
			    var collection = new Array();
			    var index = 0;		 
			    for ( var e = tr.nextSibling; !isNull(e) ; e = e.nextSibling) {
				    if (!isNull(e) && !isNull(e.tagName) && e.tagName == 'TR'){
				        var currentLevel = getTreeLevel(e);
				        if (currentLevel <= level) break;
				        if(e.style.display == '') {
					        e.style.display = 'none';
				        }else{
					        if (currentLevel != level + 1) continue;
					        e.style.display = '';
					        var imgClickable = getImgClickableElementByTr(e);
					        if (!isNull(imgClickable)){
						        if (!isNull(imgClickable.getAttribute('isOpen')) && imgClickable.getAttribute('isOpen') =='true'){
							        imgClickable.setAttribute('isOpen', 'false');
		                            imgClickable.setAttribute('src', basePath+'<%=request.getContextPath() %>/image/plus.gif');
							        collection[index] = imgClickable;
							        index++;
						        }
					        }
				        }
		            }
			    }
		    	
			    if (index > 0){
				    for (i=0;i<=index;i++){
					    displayChildren(collection[i]);
				    }
			    }
		    }
		}
	
		function folderSelect(){
			$("#fileFrame").attr("src","siteNewsModel/showModel.do");
		}
	</script>
</head>

	<c:forEach items="${list}" var="list">
		${list}		
	</c:forEach>
	
	<body>
	<button type="button" onclick="readFile()""></button>
		<div class="widget-content nopadding" style="margin-top: 20px">
			<table width="99%" align="center" valign="top">
			    <tr>
				    <td width="25%"  valign="top">
				   		<div class="widget-box">
						<div class="widget-content nopadding">						
							<table class="table-condensed table table-bordered table-striped table-hover">
								<tbody>
									<tr>
										<td>
											<img align="absmiddle" style="cursor:pointer" onClick="displayChildren();" isAjax="true" isOpen="false" id="" src="<%=request.getContextPath() %>/image/plus.gif" />						
											<img align="absmiddle" border="0" src="<%=request.getContextPath() %>/image/folder.gif" />&nbsp;
											<a href="#" onclick="folderSelect()">根目录</a>
										</td>
									</tr>
								</tbody>
							</table>		
						</div>
				      	</div>
				    </td>
				    <td>
				    </td>
				    <td>
						<iframe width="100%" height="0" name="fileFrame" src="" id="fileFrame" framespacing="0" border="false"  frameborder="0"></iframe>
				    </td>
			    </tr>
		    </table>
		</div>
	</body>
</html>