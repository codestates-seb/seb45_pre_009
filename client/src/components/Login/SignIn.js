import axios from "axios";
import { useState } from "react";

// import { useNavigate } from "react-router-dom";

const SignIn = () => {
    //입력받은 이메일, 비번, 유저이름 저장용
    const [userName , setUserName] = useState('');
    const [email , setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordError, setPasswordError] = useState('')//비밀번호 오류 메세지를 위한 상태변수

    // const navigate = useNavigate();

    //비밀번호 유효성 검사 함수
    const isPasswordValid = () => {
        const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
        return regex.test(password);
    }

    //입력받은 정보를 서버에 post
    const handleSubmit = async (e) => {
        e.preventDefault();

        if(!isPasswordValid()){
            setPasswordError('8~16글자의 알파벳,숫자,특수문자를 최소1개이상 포함한 비밀번호여야 합니다.')
        }

        try {
            const response = await axios.post("http://3.39.55.166:8080/members",{
                "displayName":userName,
                "email":email,
                "password":password
            })

            console.log('user registered successfully',response.data)

            // navigate("./main");

        } catch (error) {
            console.error('Error registering user',error)
        }
    }
    

    return (
        <>
        <div>
        <form onSubmit={handleSubmit}>
       

            <div>
                <div>
                <label>Display Name</label>
                </div>
                <input
                    className="w-[270px] border border-gray-300 p-2 rounded-md"
                    type="text"
                    value={userName}
                    onChange={(e)=>setUserName(e.target.value)}
                />
            </div>

            <div>
                <div>
                <label>Email</label>
                </div>
                <input
                    className="w-[270px] border border-gray-300 p-2 rounded-md"
                    type="text"
                    value={email}
                    onChange={(e)=>setEmail(e.target.value)}
                />
            </div>

            <div>
                <div>
                <label>Password</label>
                </div>
                <input
                    className="w-[270px] border border-gray-300 p-2 rounded-md"
                    type="password"
                    value={password}
                    onChange={(e)=>{
                        setPassword(e.target.value)
                        setPasswordError('')
                    }}
                />
            </div>
            {passwordError && <div className="w-[270px] text-red-500 text-xs justify-center items-center">{passwordError}</div>}
            <div className="flex justify-center items-center bg-[#58ACFA] mt-10 md-4 text-[white] border rounded-md h-[45px]">
            <button type="submit">Sign Up</button>
            </div>
        </form>
        </div>
        </>
    )


}

export default SignIn;