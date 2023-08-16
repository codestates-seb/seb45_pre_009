
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

export function postApi(url, data) {
    
    return axios.post(`${apiUrl}/${url}`, data)
    .then((response) => {
        console.log(response.data);
        return response.data;
    })
        .catch((error) => {
        console.log(error);
        throw error;
    });

}