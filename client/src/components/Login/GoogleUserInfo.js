import { useEffect } from "react";
import axios from "axios";

const GoogleUserInfo = ({isLogin,setIsLogin}) => {
    // const [userName, setUserName] = useState();
    useEffect(() => {
        const params = new URL(document.location.toString()).searchParams;
        const code = params.get("code");
        const GOOGLE_ID = process.env.REACT_APP_GOOGLE_CLIENT_ID;
        const GOOGLE_REDIRECT_URI = process.env.REACT_APP_GOOGLE_API_URI;
        const GOOGLE_SECRET = process.env.REACT_APP_GOOGLE_CLIENT_SECRET;
        const grantType = "authorization_code";

        axios
            .post(
                `https://oauth2.googleapis.com/token?code=${code}&client_id=${GOOGLE_ID}&redirect_uri=${GOOGLE_REDIRECT_URI}&grant_type=${grantType}&client_secret=${GOOGLE_SECRET}&scope=https://www.googleapis.com/auth/userinfo.profile`,
                {},
                {
                    headers: {
                        "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
                    },
                }
            )
            .then((res) => {
                axios
                    .get(
                        `https://www.googleapis.com/oauth2/v2/userinfo?access_token=${res.data.access_token}`,
                        {},
                        {
                            headers: {
                                Authorization: `Bearer ${res.data.access_token}`,
                                "Content-type":
                                    "application/x-www-form-urlencoded;charset=utf-8",
                            },
                        }
                    )
                    .then((res) => {
                        console.log(res);
                        sessionStorage.setItem("username", res.data.name);
                    });
            })
            .catch((Error) => {
                console.log(Error);
            });
    }, [setIsLogin]);

    return <></>;
};
export default GoogleUserInfo;