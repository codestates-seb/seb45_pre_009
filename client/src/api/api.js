
import axios from 'axios'


export const localapiUrl = `http://303c-14-52-249-197.ngrok-free.app`;
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

// export function getApi(url) {

//     return axios.get(`${apiUrl}/${url}`)
//     .then((response)=>{
//         console.log("Response Data",response.data)
//         console.log("Response received:", response);
//         return response.data;
//     }).catch((Error)=>{

//         console.log(Error);
//         throw Error;
//     })


// }

export function getApi(url) {
    const fullUrl = `${apiUrl}/${url}`;
    console.log("api:", fullUrl);

    return axios.get(fullUrl)
    .then((response)=>{
        console.log("Response Data:", response.data);
        console.log("Response received:", response);
        return response.data;
    }).catch((Error)=>{
        console.log("Error occurred:", Error); // 오류가 발생한 경우도 출력할 수 있습니다.
        throw Error;
    });
}

export function postApi(url, data) {
    const fullUrl = `${apiUrl}/${url}`;
    console.log("api:", fullUrl); // 여기에 URL을 로깅합니다.

    return axios.post(fullUrl, data, { headers })
    .then((response) => {
        console.log("Response Data",response.data);
        console.log("Response received:", response);
        console.log(headers);
        return response.data;
    })
        .catch((error) => {
        console.log(error);
        throw error;
    });

}


// export function postApi(url, data) {
//     return axios.post(`${apiUrl}/${url}`, data, { headers })
//     .then((response) => {
//         console.log("Response Data",response.data);
//         console.log("Response received:", response);
//         console.log(headers);
//         return response.data;
//     })
//         .catch((error) => {
//         console.log(error);
//         throw error;
//     });

// }

export function patchApi(url, data) {
    const fullUrl = `${apiUrl}/${url}`;
    console.log("api:", fullUrl); 

    return axios.patch(fullUrl, data, { headers })
    .then((response) => {
        console.log("Response Data",response.data);
        console.log("Response received:", response);
        return response.data;
    })
        .catch((error) => {
        console.log(error);
        throw error;
    });

}

export function deleteApi(url, data) {
    const fullUrl = `${apiUrl}/${url}`;
    console.log("api:", fullUrl); 

    return axios.delete(fullUrl, data, { headers })
    .then((response) => {
        console.log("Response Data",response.data);
        console.log("Response received:", response);
        return response.data;
    })
        .catch((error) => {
        console.log(error);
        throw error;
    });

}

// export function patchApi(url, data) {
//     return axios.patch(`${apiUrl}/${url}`, data, { headers })
//     .then((response) => {
//         console.log("Response Data",response.data);
//         console.log("Response received:", response);
//         return response.data;
//     })
//         .catch((error) => {
//         console.log(error);
//         throw error;
//     });

// }