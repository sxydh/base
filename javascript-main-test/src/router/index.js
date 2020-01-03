import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: "history",
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: resolve => require(['@/components/HelloWorld'], resolve)
    },
    {
      path: '/vue',
      name: 'Vue',
      meta: [],
      component: resolve => require(['@/components/vue/App'], resolve),
      children: [
        {
          path: 'pass_value',
          name: 'PassValue',
          component: resolve => require(['@/components/vue/pass_value/App'], resolve)
        },
        {
          path: 'simple_store',
          name: 'SimpleStore',
          component: resolve => require(['@/components/vue/simple_store/App'], resolve),
          children: [
            {
              path: 'dis_a',
              name: 'DisplayA',
              component: resolve => require(['@/components/vue/simple_store/DisplayA'], resolve)
            },
            {
              path: 'dis_b',
              name: 'DisplayB',
              component: resolve => require(['@/components/vue/simple_store/DisplayB'], resolve)
            },
          ]
        },
        {
          path: 'store',
          name: 'Store',
          component: resolve => require(['@/components/vue/store/App'], resolve),
          children: [
            {
              path: 'dis_a',
              name: 'DisplayA',
              component: resolve => require(['@/components/vue/store/DisplayA'], resolve)
            },
            {
              path: 'dis_b',
              name: 'DisplayB',
              component: resolve => require(['@/components/vue/store/DisplayB'], resolve)
            },
          ]
        },
      ]
    },
    {
      path: '/css',
      name: 'CSS',
      component: resolve => require(['@/components/css/App'], resolve),
      meta: [],
      children: [
        {
          path: 'position',
          name: 'Position',
          component: resolve => require(['@/components/css/Position'], resolve),
        }
      ]
    }
  ]
})
