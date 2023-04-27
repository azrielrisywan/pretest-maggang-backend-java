--
-- PostgreSQL database dump
--

-- Dumped from database version 14.4
-- Dumped by pg_dump version 14.4

-- Started on 2023-04-27 15:10:10

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
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 3363 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 214 (class 1259 OID 16407)
-- Name: produk; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produk (
    id integer NOT NULL,
    nama character varying,
    jenis character varying,
    berat integer,
    produsen_id integer,
    harga numeric,
    spesifikasi character varying
);


ALTER TABLE public.produk OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16406)
-- Name: produk_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produk_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produk_id_seq OWNER TO postgres;

--
-- TOC entry 3364 (class 0 OID 0)
-- Dependencies: 213
-- Name: produk_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produk_id_seq OWNED BY public.produk.id;


--
-- TOC entry 212 (class 1259 OID 16396)
-- Name: produsen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produsen (
    id integer NOT NULL,
    nama character varying,
    kode character varying,
    alamat character varying
);


ALTER TABLE public.produsen OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16395)
-- Name: produsen_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produsen_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produsen_id_seq OWNER TO postgres;

--
-- TOC entry 3365 (class 0 OID 0)
-- Dependencies: 211
-- Name: produsen_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produsen_id_seq OWNED BY public.produsen.id;


--
-- TOC entry 216 (class 1259 OID 16416)
-- Name: transaksi; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaksi (
    id integer NOT NULL,
    id_user integer,
    status smallint DEFAULT 0 NOT NULL
);


ALTER TABLE public.transaksi OWNER TO postgres;

--
-- TOC entry 3366 (class 0 OID 0)
-- Dependencies: 216
-- Name: COLUMN transaksi.status; Type: COMMENT; Schema: public; Owner: postgres
--

COMMENT ON COLUMN public.transaksi.status IS '0 = on cart
1 = transaksi selesai';


--
-- TOC entry 221 (class 1259 OID 17366)
-- Name: transaksi_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaksi_detail (
    id_transaksi integer,
    id_produk integer,
    kuantitas integer
);


ALTER TABLE public.transaksi_detail OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 17365)
-- Name: transaksi_detail_id_transaksi_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaksi_detail_id_transaksi_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaksi_detail_id_transaksi_seq OWNER TO postgres;

--
-- TOC entry 3367 (class 0 OID 0)
-- Dependencies: 220
-- Name: transaksi_detail_id_transaksi_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaksi_detail_id_transaksi_seq OWNED BY public.transaksi_detail.id_transaksi;


--
-- TOC entry 215 (class 1259 OID 16415)
-- Name: transaksi_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaksi_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaksi_id_seq OWNER TO postgres;

--
-- TOC entry 3368 (class 0 OID 0)
-- Dependencies: 215
-- Name: transaksi_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaksi_id_seq OWNED BY public.transaksi.id;


--
-- TOC entry 219 (class 1259 OID 17360)
-- Name: transaksi_id_users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.transaksi_id_users_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.transaksi_id_users_seq OWNER TO postgres;

--
-- TOC entry 3369 (class 0 OID 0)
-- Dependencies: 219
-- Name: transaksi_id_users_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.transaksi_id_users_seq OWNED BY public.transaksi.id_user;


--
-- TOC entry 218 (class 1259 OID 17328)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    nama character varying,
    telepon character varying
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 17327)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3370 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 3198 (class 2604 OID 16410)
-- Name: produk id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produk ALTER COLUMN id SET DEFAULT nextval('public.produk_id_seq'::regclass);


--
-- TOC entry 3197 (class 2604 OID 16399)
-- Name: produsen id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produsen ALTER COLUMN id SET DEFAULT nextval('public.produsen_id_seq'::regclass);


--
-- TOC entry 3199 (class 2604 OID 16419)
-- Name: transaksi id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaksi ALTER COLUMN id SET DEFAULT nextval('public.transaksi_id_seq'::regclass);


--
-- TOC entry 3201 (class 2604 OID 17331)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3350 (class 0 OID 16407)
-- Dependencies: 214
-- Data for Name: produk; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produk (id, nama, jenis, berat, produsen_id, harga, spesifikasi) FROM stdin;
3	iPhone 14 Pro	Handphone	\N	3	19000000	~ini spek~
4	Samsung Galaxy S21	Handphone	\N	1	10000000	~ini spek~
5	Google Pixel 6	Handphone	\N	4	3500000	~ini spek~
6	Macbook Pro 14" 2020	Laptop	\N	3	16700000	~ini spek~
7	Airpods Gen 2	Audio	\N	3	2100000	~ini spek~
\.


--
-- TOC entry 3348 (class 0 OID 16396)
-- Dependencies: 212
-- Data for Name: produsen; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produsen (id, nama, kode, alamat) FROM stdin;
1	Samsung	SM	Korea
3	Apple	AP	Singapore
4	Google	GO	United States
\.


--
-- TOC entry 3352 (class 0 OID 16416)
-- Dependencies: 216
-- Data for Name: transaksi; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaksi (id, id_user, status) FROM stdin;
17	1	1
18	1	0
19	1	0
\.


--
-- TOC entry 3357 (class 0 OID 17366)
-- Dependencies: 221
-- Data for Name: transaksi_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaksi_detail (id_transaksi, id_produk, kuantitas) FROM stdin;
17	3	1
18	5	1
19	6	1
\.


--
-- TOC entry 3354 (class 0 OID 17328)
-- Dependencies: 218
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, nama, telepon) FROM stdin;
1	Azriel	0828932132180
\.


--
-- TOC entry 3371 (class 0 OID 0)
-- Dependencies: 213
-- Name: produk_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produk_id_seq', 7, true);


--
-- TOC entry 3372 (class 0 OID 0)
-- Dependencies: 211
-- Name: produsen_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produsen_id_seq', 8, true);


--
-- TOC entry 3373 (class 0 OID 0)
-- Dependencies: 220
-- Name: transaksi_detail_id_transaksi_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaksi_detail_id_transaksi_seq', 1, false);


--
-- TOC entry 3374 (class 0 OID 0)
-- Dependencies: 215
-- Name: transaksi_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaksi_id_seq', 19, true);


--
-- TOC entry 3375 (class 0 OID 0)
-- Dependencies: 219
-- Name: transaksi_id_users_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaksi_id_users_seq', 1, false);


--
-- TOC entry 3376 (class 0 OID 0)
-- Dependencies: 217
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 1, true);


--
-- TOC entry 3205 (class 2606 OID 16414)
-- Name: produk produk_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produk
    ADD CONSTRAINT produk_pk PRIMARY KEY (id);


--
-- TOC entry 3203 (class 2606 OID 16403)
-- Name: produsen produsen_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produsen
    ADD CONSTRAINT produsen_pk PRIMARY KEY (id);


--
-- TOC entry 3207 (class 2606 OID 17333)
-- Name: users users_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pk PRIMARY KEY (id);


-- Completed on 2023-04-27 15:10:11

--
-- PostgreSQL database dump complete
--

