function repetition() {
	alert("등록된 아이디입니다.");
	location.href = "./join.jlab";
}

function cancle() {
	location.href = "./gesipan.jlab";
}

function gasi_delete(a_num, r_num) {
	location.href = "./gesipan_reple_delete.jlab?anum="+a_num+"&rnum="+r_num;
}