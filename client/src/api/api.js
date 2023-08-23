
import axios from 'axios'


export const apiUrl = `http://3.39.55.166:8080`;
export const jsonUrl = `http://localhost:5002`;

function getToken() {
    return localStorage.getItem('jwt');
  }

  const token = getToken();
  const headers = {
    'Authorization': `${token}`
  };

// import { getApi } from '../api/api.js'; 복사해서 사용

export function getApi(url) {

    return axios.get(`${apiUrl}/${url}`)
    .then((response)=>{
        console.log(response.data)
        return response.data;
    }).catch((Error)=>{

        console.log(Error);
        throw Error;
    })


}


export function postApi(url, data) {
    return axios.post(`${apiUrl}/${url}`, data, { headers })
    .then((response) => {
        console.log(response.data);
        console.log(headers);
        return response.data;
    })
        .catch((error) => {
        console.log(error);
        throw error;
    });

}

export function patchApi(url, data) {
    return axios.patch(`${apiUrl}/${url}`, data, { headers })
    .then((response) => {
        console.log(response.data);
        return response.data;
    })
        .catch((error) => {
        console.log(error);
        throw error;
    });

}