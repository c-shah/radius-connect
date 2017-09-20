<br>
    Name : Chintan Shah
<br>
    Phone : 206 419 0241
<#if loggedInToConnectedApp == true>
  Add Contact to Salesforce ? <a href="/radius.connect.add.contact"> add contact </a>
<#else>

</#if>

<br>
<br>

queryParams ${queryParams}

<a href="javascript:void(0);" onclick="refresh();"> REFRESH </a>
<br/>
<a href="javascript:void(0);" onclick="refresh2();"> REFRESH2 </a>
<br/>
<a href="javascript:void(0);" onclick="refresh3();"> REFRESH3 </a>
<br/>
<a href="javascript:void(0);" onclick="refresh4();"> REFRESH4 </a>
<br/>
<a href="javascript:void(0);" onclick="refresh5();"> REFRESH5 </a>
<br/>


<script>
    function refresh() {
        parent.location.reload();
    }

    function refresh2() {
        window.parent.location.href = "https://na59.salesforce.com/001f4000005anWk";
    }

    function refresh3() {
        parent.location.href=parent.location.href
    }

    function refresh4() {
        window.parent.location.href =  window.parent.location.href;
    }

    function refresh5() {
        var url = (window.location != window.parent.location)
                ? document.referrer
                : document.location.href;
        alert( url );
        window.parent.location.href =  url;
    }

</script>

<br>
<br>
<br>
<br>
