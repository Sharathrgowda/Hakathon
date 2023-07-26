import React, { useRef } from 'react'
import { FaSearch } from 'react-icons/fa';
import { UilTimes } from '@iconscout/react-unicons'
import './SearchBar.css'

const SearchBar = (props) => {

    const inputEle = useRef('');

    const getSearchData = () => {
        props.searchValue(inputEle.current.value);
    };

    return (
        <div>
            <form className="search-box">
                <input
                    name="search"
                    type="text"
                    placeholder="Search by employee name"
                    ref={inputEle}
                    value={props.data}
                    onChange={getSearchData}
                />
                {inputEle.current.value ? <UilTimes style={{ color: "black" }} onClick={() =>  props.searchValue('')} /> : <FaSearch color="black" />}
            </form>
        </div>
    )
}

export default SearchBar
