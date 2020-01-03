import axios from 'axios'
import { Message } from 'element-ui';

export default {
    asyncPost(config) {
        let url = process.env.API_ROOT + config.url;
        let data = config.data;
        console.log('myHttp => asyncPost( ' + JSON.stringify(config) + ' )');
        return new Promise((resolve) => {
            axios({
                withCredentials: true,
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                url: url,
                data: data
            }).then((suc) => {
                console.log('myHttp => asyncPost => suc( ' + JSON.stringify(suc.data) + ' )');
                if (suc.data.msg) {
                    Message.error(suc.data.msg);
                    resolve([null, suc.data.msg]);
                    return;
                }
                resolve([suc.data, null]);
            }).catch(error => {
                console.log('myHttp => asyncPost => catch( ' + error + ' )');
                Message.error(error + '');
                resolve([null, error]);
            })
        });
    },
    asyncGet(config) {
        let url = process.env.API_ROOT + config.url;
        console.log('myHttp => asyncGet( ' + JSON.stringify(config) + ' )');
        return new Promise((resolve) => {
            axios({
                withCredentials: true,
                method: 'GET',
                url: url,
            }).then((suc) => {
                console.log('myHttp => asyncGet => suc( ' + JSON.stringify(suc.data) + ' )');
                if (suc.data.msg) {
                    Message.error(suc.data.msg);
                    resolve([null, suc.data.msg]);
                    return;
                }
                resolve([suc.data, null]);
            }).catch(error => {
                console.log('myHttp => asyncGet => catch( ' + error + ' )');
                Message.error(error + '');
                resolve([null, error]);
            })
        });
    }
}