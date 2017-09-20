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
<a href="javascript:void(0);" onclick="refresh2();"> REFRESH2 </a>
<a href="javascript:void(0);" onclick="refresh3();"> REFRESH3 </a>


<script>
    function refresh() {
        parent.location.reload();
    }

    function refresh2() {
        window.parent.location.href = "http://www.stackoverflow.com";
    }

    function refresh3() {
        parent.location.href=parent.location.href
    }
</script>

<br>
<br>
<br>
<br>
