import axios from "axios";
import { useState } from "react";

// import { useNavigate } from "react-router-dom";

const SignIn = () => {
    //입력받은 이메일, 비번, 유저이름 저장용
    const [userName , setUserName] = useState('');
    const [email , setEmail] = useState('');
    const [password, setPassword] = useState('');

    // const navigate = useNavigate();

    //입력받은 정보를 서버에 post
    const handleSubmit = async (e) => {
        e.preventDefault();

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
                    type="text"
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                />
            </div>
            <div className="flex justify-center item-center bg-[#58ACFA] mt-10 md-4 text-[white] border rounded-md h-[45px]">
            <button type="submit">Sing Up</button>
            </div>
        </form>
        </div>
        </>
    )


}

export default SignIn;