function sendData(frm)
{
   /* var x=document.getElementById("inputfile");*/
   var x=frm.inputfile;
    var y=document.getElementById("sp");
    if(y!=null)
        y.innerHTML="";
    if(x.files.length=="")
    {
        alert("Select atleast one");
        return false;
    }else
    {
        for(var i=0;i<x.files.length;i++)
        {
            var f=x.files[0];
            name=f.name;
        }
        frm.action="uploaddataservlet?flname="+name;
        return true;
    }
}
