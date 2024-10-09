import React, { useEffect, useState } from 'react'
import {
    MapContainer,
    TileLayer,
    Marker,
    Popup,
    useMapEvents
} from 'react-leaflet'
import 'leaflet/dist/leaflet.css'
import L from 'leaflet'

// Fix marker icon issue
// delete L.Icon.Default.prototype._getIconUrl;
// L.Icon.Default.mergeOptions({
//   iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
//   iconUrl: require('leaflet/dist/images/marker-icon.png'),
//   shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
// });
const Map = () => {
    const [latlang,setLatLang] = useState([28.4595, 77.0266]);
    useEffect(() => {
        navigator.geolocation.getCurrentPosition((location)=>{setLatLang([location.coords.latitude,location.coords.longitude])});
    })
    const [position, setPosition] = useState(null)
    const window_width = `${window.innerWidth/2.5}px`;
    const MapClickHandler = () => {
        useMapEvents({
            click: event => {
                const { lat, lng } = event?event.latlng:{lat:28.4595, lng:77.0266};
                setPosition([lat, lng])
                console.log(`Latitude: ${lat}, Longitude: ${lng}`)
            }
        })
        return null
    }
    return (
        <MapContainer
            center={[28.4595, 77.0266]}
            zoom={14}
            className={`w-full h-full rounded-xl border-yellow-400 border-2`}
            style={{width:window_width}}
        >
            <TileLayer
                url='https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png'
                attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            />
            <MapClickHandler />
            {position && (
                <Marker position={position}>
                    <Popup>
                        Latitude: {position[0]} <br /> Longitude: {position[1]}
                    </Popup>
                </Marker>
            )}
            {/* {latlang && (
                <Marker position={position}>
                    <Popup>
                        Latitude: {latlang[0]} <br /> Longitude: {latlang[1]}
                    </Popup>
                </Marker>
            )} */}
        </MapContainer>
    )
}

export default Map
