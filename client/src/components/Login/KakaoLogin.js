import {useNavigate} from "react-router-dom"
import { useEffect,useState } from "react"

const SocialKAKAO = () => {
    
    const CLIENT_ID = `${process.env.REACT_APP_REST_API_KEY}`;
    const REDIRECT_URI = `${process.env.REACT_APP_REST_API_URI}`;
    const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
    // const code = new URL(window.location.href).searchParams.get("code");
    // const navigate = useNavigate();
    // const [Code,setCode] = useState("");

    const handleLogin = () => {
        window.location.href = KAKAO_AUTH_URL;
    }

    // useEffect(()=>{
    //     if(code?.length>1){
    //         setCode(code);
    //     }
    // },[code])

    // useEffect(()=>{
    //     if(Code){
    //         console.log(Code);
    //         navigate(`/main?code=${Code}`);
    //     }
    // },[Code,navigate]);


    return (
        <>
        <button onClick={handleLogin}>
            Sign in with KAKAO
        </button>
        </>
    )
}

export default SocialKAKAO;