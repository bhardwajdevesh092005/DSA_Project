import './App.css'
import Map from './Components/Map'
function App () {
  return (
    <div className='bg-[#254360] flex flex-col justify-center w-screen h-screen text-white'>
      <div className='flex mx-auto flex-col justify-center text-3xl h-full text-yellow-400 my-20'>
        <div className='flex h-full space-x-10'>
          <div className='flex flex-col w-full  h-full   my-auto space-y-10'>
            <div className='w-full text-center'>Pickup Location</div>
            <Map />
          </div>
          <div className='flex flex-col w-full rounded-xl h-full my-auto space-y-10'>
            <div className='w-full text-center'>Delivery Location</div>
            <Map />
          </div>
        </div>
        <div className='w-fit mx-auto'>
          <div className='w-full text-center h-fit my-auto bg-gray-900 p-3 rounded-xl mt-10'>
            <a href="/result">Submit</a>
          </div>
        </div>
      </div>
    </div>
  )
}

export default App
