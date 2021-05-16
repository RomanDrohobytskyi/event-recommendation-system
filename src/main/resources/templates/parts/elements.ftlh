<#macro input id="" name="" value="" type="" placeholder="" onclick="" class=""
style="text-align:center; width: 250px;" step="" min="" max="" pattern="">
    <input id="${id}" name="${name}" value="${value}" type="${type}" placeholder="${placeholder}"
           onfocus="this.placeholder = ''" onblur="this.placeholder = '${placeholder}'" class="${class}"
           style="${style}" step="${step}" min="${min}" max="${max}" />
</#macro>

