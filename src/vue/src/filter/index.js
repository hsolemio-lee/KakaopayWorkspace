import datetimeFormat from './datetimeFormat';
export default {
    install(Vue) {
        Vue.filter(datetimeFormat.name, datetimeFormat.logic);
    }
}