import Vue from 'vue';

const requireComponent = require.context(
    '@/components/common',
    false,
    /Base[A-Z]\w+\.(vue|js)$/
);

requireComponent.keys().forEach((filename) => {
    const componentConfig = requireComponent(filename);
    const componentName = filename.replace(/^\.\/(.*)\.\w+$/, '$1')

    Vue.component(componentName, componentConfig.default || componentConfig);
});

export default {};