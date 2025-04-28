import { Admin } from "react-admin";
import { Layout } from "./Layout";
import resources from "./modules";

export const App = () => <Admin layout={Layout}>
    {resources()}
</Admin>;
