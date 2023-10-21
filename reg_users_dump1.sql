--
-- PostgreSQL database dump
--

-- Dumped from database version 15.4
-- Dumped by pg_dump version 15.4

-- Started on 2023-10-20 14:55:06

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 32944)
-- Name: pgcrypto; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA public;


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION pgcrypto; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION pgcrypto IS 'cryptographic functions';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 32982)
-- Name: ref_Password; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ref_Password" (
    id bigint NOT NULL,
    password character varying(100) NOT NULL
);


ALTER TABLE public."ref_Password" OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 32981)
-- Name: ref_Password_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."ref_Password_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."ref_Password_id_seq" OWNER TO postgres;

--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 217
-- Name: ref_Password_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."ref_Password_id_seq" OWNED BY public."ref_Password".id;


--
-- TOC entry 216 (class 1259 OID 32938)
-- Name: ref_User; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."ref_User" (
    id bigint NOT NULL,
    name character varying(64) NOT NULL,
    email character varying(135) NOT NULL,
    id_password bigint NOT NULL,
    date timestamp without time zone NOT NULL
);


ALTER TABLE public."ref_User" OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 32937)
-- Name: ref_User_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."ref_User_id_user_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."ref_User_id_user_seq" OWNER TO postgres;

--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 215
-- Name: ref_User_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."ref_User_id_user_seq" OWNED BY public."ref_User".id;


--
-- TOC entry 3216 (class 2604 OID 32985)
-- Name: ref_Password id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ref_Password" ALTER COLUMN id SET DEFAULT nextval('public."ref_Password_id_seq"'::regclass);


--
-- TOC entry 3215 (class 2604 OID 32941)
-- Name: ref_User id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ref_User" ALTER COLUMN id SET DEFAULT nextval('public."ref_User_id_user_seq"'::regclass);


--
-- TOC entry 3367 (class 0 OID 32982)
-- Dependencies: 218
-- Data for Name: ref_Password; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."ref_Password" (id, password) FROM stdin;
1	$1$jw7hf4H4$TerU23I3gs.4RdBrmdmHJ/
\.


--
-- TOC entry 3365 (class 0 OID 32938)
-- Dependencies: 216
-- Data for Name: ref_User; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."ref_User" (id, name, email, id_password, date) FROM stdin;
1	login_1	email@gmail.com	1	2023-10-20 11:02:20.900939
\.


--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 217
-- Name: ref_Password_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."ref_Password_id_seq"', 1, true);


--
-- TOC entry 3377 (class 0 OID 0)
-- Dependencies: 215
-- Name: ref_User_id_user_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."ref_User_id_user_seq"', 1, true);


--
-- TOC entry 3220 (class 2606 OID 32987)
-- Name: ref_Password ref_Password_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ref_Password"
    ADD CONSTRAINT "ref_Password_pkey" PRIMARY KEY (id);


--
-- TOC entry 3218 (class 2606 OID 32943)
-- Name: ref_User ref_User_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ref_User"
    ADD CONSTRAINT "ref_User_pkey" PRIMARY KEY (id);


--
-- TOC entry 3221 (class 2606 OID 32988)
-- Name: ref_User password; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."ref_User"
    ADD CONSTRAINT password FOREIGN KEY (id_password) REFERENCES public."ref_Password"(id) NOT VALID;


-- Completed on 2023-10-20 14:55:06

--
-- PostgreSQL database dump complete
--

