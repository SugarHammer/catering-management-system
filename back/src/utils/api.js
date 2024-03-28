// axios拦截器
import axios from 'axios'

// 请求拦截器，在请求头上添加token
axios.interceptors.request.use(config => {
  console.log("发起请求")
  if (localStorage.getItem('Authorization')) {
    config.headers.Authorization = localStorage.getItem('Authorization');
  } else {
    config.headers.Authorization = ''
  }
  // console.log(config);
  return config;
}, err => {
  return Promise.reject(err);
})

// 响应拦截器
axios.interceptors.response.use(config => {
  console.log("响应回来了")
  return config;
}, err => {
  console.log(err)
})

let base = ''
// 发送post请求到后端
export const postServerRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params
  })
}
// 发送get请求到后端
export const getServerRequest = (url) => {
  return axios({
    method: 'get',
    url: `${base}${url}`
  })
}
