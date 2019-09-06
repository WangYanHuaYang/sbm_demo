var instance = axios.create({
    baseUrl:getRootPath(),
    timeout:1000,
    auth:{
        username:'qwer',
        password:'123456'
    }
})
