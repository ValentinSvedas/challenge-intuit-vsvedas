import { Admin } from "react-admin";
import { Layout } from "./Layout";
import resources from "./modules";
import { dataProvider } from "./DataPrivder";

export const App = () => <Admin layout={Layout} dataProvider={dataProvider}>
    {resources()}
</Admin>;
