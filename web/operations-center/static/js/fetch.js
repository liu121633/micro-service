export default {
    baseUri: "http://127.0.0.1:8802",
    post: function (vue, uri, data, success) {
        const headers = {
            "Content-Type": "application/json"
        };
        const token = vue.$cookies.get("token");
        if (token !== undefined) {
            headers.Authorization = token;
        }
        fetch(this.baseUri + uri, {
            method: 'post',
            body: JSON.stringify(data),
            headers: headers
        }).then(response => {
            response.json()
                .then(data => {
                        if (data.status === 'OK') {
                            success(data)
                        } else {
                            vue.$notify({
                                title: '来自服务器的错误消息',
                                message: data.message,
                                type: 'error'
                            });
                        }
                    }
                )
                .catch(error => console.log("发生错误", error))
        }).catch(error => console.log("发生错误", error));
    },
    get(vue, uri, success) {
        const headers = {
            "Content-Type": "application/json"
        };
        const token = vue.$cookies.get("token");
        if (token !== undefined) {
            headers.Authorization = token;
        }
        fetch(this.baseUri + uri, {
            method: 'get',
            headers: headers
        }).then(response => {
            response.json()
                .then(data => {
                        if (data.status === 'OK') {
                            success(data)
                        } else {
                            vue.$notify({
                                title: '来自服务器的错误消息',
                                message: data.message,
                                type: 'error'
                            });
                        }
                    }
                )
                .catch(error => console.log("发生错误", error))
        }).catch(error => console.log("发生错误", error));
    }
}