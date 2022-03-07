export default {
    name: 'datetimeFormat',
    logic: datetimeFormat
}

function datetimeFormat(value) {
    let datetime = new Date(value);
    return datetime.format('yyyy-MM-dd HH:mm:ss');
}