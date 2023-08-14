
import axios from 'axios'

const apiUrl = `http://localhost:5002`;

// import { getApi } from '../api/api.js'; 복사해서 사용

export function getApi(url) {

    return axios.get(`${apiUrl}/${url}`)
    .then((Response)=>{
        console.log(Response.data)
        return Response.data;
    }).catch((Error)=>{
        console.log(Error);
        throw Error;
    })

}
