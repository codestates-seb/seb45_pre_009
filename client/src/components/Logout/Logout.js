

const Logout = ({setIsLogin,isLogin}) => {

    const handleLogout = () =>{
        setIsLogin(false)
        sessionStorage.setItem("isLogin",false);
        localStorage.setItem("jwt","");
        localStorage.setItem("refresh_token","");
        
    }


    return (
        <>
        <button onClick={handleLogout}>Logout</button>
        </>
    )
}

export default Logout