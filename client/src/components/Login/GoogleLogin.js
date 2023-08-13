

const SocialGoogle = () => {
    // API, URI 받아 오기
    // URL 설정
    const GOOGLE_ID = process.env.REACT_APP_GOOGLE_CLIENT_ID;
    const GOOGLE_REDIRECT_URI = process.env.REACT_APP_GOOGLE_API_URI;
    const GOOGLE_AUTH_URL = `https://accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=${GOOGLE_ID}&scope=openid%20profile%20email&redirect_uri=${GOOGLE_REDIRECT_URI}`

    // 로그인 버튼을 눌렀을 때 해당 링크로 이동하게끔 설정
    const GoogleLogin = () => {
        window.location.href = GOOGLE_AUTH_URL;
    }

    return (
        <>
        <div>
            <button
                onClick={GoogleLogin}
                
            >Sign in with GOOGLE</button>
        </div>
        </>
    )
}

export default SocialGoogle;