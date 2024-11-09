import { useState } from 'react';
import './App.css'
import Map from './Components/Map'
import { Link } from 'react-router-dom';
function App () {
  const [location1,setLocation1] = useState({});
  const [location2,setLocation2] = useState({});
  const handleLocation1Change = (newLoc)=>{
    setLocation1(newLoc);
  }
  const handleLocation2Change = (newLoc)=>{
    setLocation2(newLoc);
  }
  return (
    <div className='bg-[#254360] flex flex-col justify-center w-screen h-screen text-white'>
      <div className='flex mx-auto flex-col justify-center text-3xl h-full text-yellow-400 my-20'>
        <div className='flex h-full space-x-10'>
          <div className='flex flex-col w-full  h-full   my-auto space-y-10'>
            <div className='w-full text-center'>Pickup Location</div>
            <Map Location={location2} onLocationChange={handleLocation2Change} />
          </div>
          <div className='flex flex-col w-full rounded-xl h-full my-auto space-y-10'>
            <div className='w-full text-center'>Delivery Location</div>
            <Map Location={location1} onLocationChange={handleLocation1Change} />
          </div>
        </div>
        <div className='w-fit mx-auto'>
          <div className='w-full text-center h-fit my-auto bg-gray-900 p-3 rounded-xl mt-10'>
            <Link to={`/result/${location1.lat}/${location1.lng}/${location2.lat}/${location2.lng}`}>Submit</Link>
          </div>
        </div>
      </div>
    </div>
  )
}

export default App
