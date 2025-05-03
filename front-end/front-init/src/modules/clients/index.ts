import AccountCircleIcon from '@mui/icons-material/AccountCircle';

import ClientList from './ClientList';
import ClientCreate from './ClientCreate';
import ClientEdit from './ClientEdit';

const clients = () => {
    return ({
                icon: AccountCircleIcon,
                list: ClientList,
                create: ClientCreate,
                edit: ClientEdit,
            }
    );
};

export default clients;