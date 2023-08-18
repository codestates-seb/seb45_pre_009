import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const NormalLogin = ({isLogin,setIsLogin}) => {
    //입력받은 이메일, 비밀번호 
    const [email , setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordError, setPasswordError] = useState('')//비밀번호 오류 메세지를 위한 상태변수
    const navigate = useNavigate();

    //비밀번호 유효성 검사 함수
    const isPasswordValid = () => {
        const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,16}$/;
        return regex.test(password);
    }

    

    const handleSubmit = async (e) => {
        e.preventDefault();

        if(!isPasswordValid()){
            setPasswordError('8~16글자의 알파벳,숫자,특수문자를 최소1개이상 포함한 비밀번호여야 합니다.')
        }

        try {
            const response = await axios.post("https://a517-14-52-249-197.ngrok-free.app/login",{
                "username": email,
                "password": password
            })

            console.log('로그인 성공',response)
            setIsLogin(true);
            sessionStorage.setItem("isLogin", true);
            console.log(response.headers.authorization)
            console.log(response.headers.refresh)
            // navigate("../");
            

        } catch (error) {
            console.error('로그인 에러',error)
        }
    }
    return (
        <>
        <div className="">
        <form onSubmit={handleSubmit}>
       

           

            <div className="md-4">
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

            <div className="mt-5">
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
            <button type="submit">Log in</button>
            </div>
        </form>
        </div>
        </>
    )

}

export default NormalLogin