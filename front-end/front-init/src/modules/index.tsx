import { Resource } from 'react-admin';
import clients from './clients';


const resources = () => (
    <>
        <Resource name="client" {...clients()} options={{ label: 'Clientes' }} />
    </>

);


export default resources;
