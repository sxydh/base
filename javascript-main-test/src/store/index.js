import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const state = {
    num: 0,
    str: "empty",
    blean: true,
};
const getters = {
    // Execute only when the dependency value changes, otherwise return the previous calculation result
    getNum: state => state.num,
    getStr: state => state.str,
    getBlean: state => state.blean
};
const mutations = {
    // The first parameter defaults to state, even if the name is not "state"
    setNum(state, newNum) {
        state.num = newNum;
    },
    setStr(state, newStr) {
        state.str = newStr;
    },
    setBlean(state, newBlean) {
        state.blean = newBlean;
    },
    update(state, payload) {
        if (payload.newNum) {
            setTimeout(() => {
                state.num = payload.newNum;
            }, 1000)
        }
        if (payload.newStr) {
            state.str = payload.newStr;
        }
        state.blean = payload.newBlean;
    }
};
const actions = {
    update({ commit }, payload) {
        setTimeout(() => {
            commit('update', payload)
        }, 1000)
    }
};

export default new Vuex.Store({
    state,
    getters,
    mutations,
    actions
})
