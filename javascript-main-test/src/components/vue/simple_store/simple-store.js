export default {
    state: {
        store_number: 0,
        store_string: 'store string'
    },
    alterNum(value) {
        this.store_number = this.store_number + value;
    },
    alterStr(str) {
        this.store_string = this.store_string + str;
    }
}