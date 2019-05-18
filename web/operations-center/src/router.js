import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes: [
        {
            path: "/",
            name: 'index',
            component: () => import('./views/Index.vue'),
            children: [
                {
                    path: 'tables',
                    component: () => import('./views/Tales.vue')
                },
                {
                    path: 'columns',
                    name: 'table_info',
                    component: () => import('./views/Columns.vue')
                }
            ]
        }
    ]
})
