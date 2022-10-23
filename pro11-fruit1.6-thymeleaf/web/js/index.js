function delFruit(fid) {
    if (confirm('是否确认')) {
        window.location.href = 'del.do?fid=' + fid;
    }
}

function page(pageNo){
    window.location.href="index?pageNo=" + pageNo;
}