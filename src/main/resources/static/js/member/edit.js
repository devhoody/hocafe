window.addEventListener("load", function() {
    let nameBox = document.querySelector(".name-box");
    let checkBeforeName = nameBox.querySelector("input[name=beforeName]");
    let AfterName = nameBox.querySelector("input[name=afterName]");
    let dupliName = document.querySelector("#dupli-name");

    // 버튼
    let nameBtn = nameBox.querySelector("input[type=submit]");

    nameBtn.onclick = async (e) => {
        console.log("=================================================")
        console.log("=================이름 변경 버튼 =================")
        console.log("=================================================")
        e.preventDefault();

        let el = e.target;

        console.log(checkBeforeName.value);

        let name = checkBeforeName.value;

        let response = await fetch(`/api/members/edit?name=${name}`);
        let result = await response.json();

        console.log("닉네임 중복(true면 중복) :" + result);

        // true면 중복
        // false면 중복 x
        if(result === false) {
            console.log(dupliName.classList)
            dupliName.classList.remove("d:none")
            console.log("없는 이름입니다");
            return;
        } else if(checkBeforeName.value === ''){
            dupliName.classList.add("d:none");
        } else {
            dupliName.classList.add("d:none")
        }

        /* --------------------------이름 변경 처리 ------------------------*/
        let afterName = AfterName.value;

        let member = {
            name : afterName
        }

        console.log(JSON.stringify(member));

        let response2 = await fetch(`api/members/edit`, {
            method: 'PUT',
            headers: {
                'Content-Type' : 'application/json'
            },
            body: JSON.stringify(member)
        })

        let result2 = await response2.json();

        console.log("변경 결과(true면 변경 완료) :" + result2);

        if(result === true){
            alert("입력하신 이름으로 변경이 완료되었습니다.");
            window.location="/"
        } else
            alert("다시 한번 입력해주세요.");
    }


})