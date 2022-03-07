import axios from 'axios';

let solAxios = createSolInstance();
function createSolInstance() {
  const solAxios = axios.create();
  solAxios.defaults.baseURL = "http://localhost:8080"
  solAxios.defaults.withCredentials = true;
  solAxios.defaults.crossDomain = true;
  return solAxios;
}

export { solAxios };
