import { createRoot } from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import Layout from './pages/Layout.jsx'
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider
} from 'react-router-dom'
import Result from './pages/Result.jsx'
const router = createBrowserRouter(
  createRoutesFromElements(
    <Route element={<Layout/>} path='/'>
      <Route element={<App/>} path=''/>
      <Route element={<Result />} path='/result' />
    </Route>
  )
)
createRoot(document.getElementById('root')).render(
  <RouterProvider router={router}/>
)
